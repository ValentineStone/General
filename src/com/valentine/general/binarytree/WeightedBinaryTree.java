package com.valentine.general.binarytree;

public class WeightedBinaryTree<T extends Comparable<T>> extends BinaryTree<T>
{
	private int weight = 0;
	private double balance = 0;
	
	
	
	public WeightedBinaryTree()
	{
		super();
	}
	
	public WeightedBinaryTree(T[] _objects)
	{
		super(_objects);
		if (_objects != null)
		{
			weight = _objects.length;
		}
	}
	
	public WeightedBinaryTree(T _object)
	{
		super(_object);
		weight = 1;
	}
	
	

	public int getWeight()
	{
		return weight;
	}
	
	private void incWeight()
	{
		weight++;
		if (getTop() != null)
		{
			((WeightedBinaryTree<T>)getTop()).incWeight();
		}
	}
	
	private int makeWeights()
	{
		return count
			+ (getLeft() != null ? ((WeightedBinaryTree<T>)getLeft()).makeWeights() : 0)
			+ (getRight() != null ? ((WeightedBinaryTree<T>)getRight()).makeWeights() : 0)
		;
	}
	
	
	
	public double getBalance()
	{
		return balance;
	}
	
	private double makeBalance()
	{
		double down;
		double up;
		
		down =
			weight - count;
		
		up =
			((WeightedBinaryTree<T>)getRoot()).weight
			- weight;
		
		if (up == 0 || down == 0)
		{
			balance = 0;
		}
		else if (up < down)
			balance = up / down;
		else
			balance = down / up;
		
		return balance;
	}
	
	private void makeBalances()
	{
		makeBalance();
		
		if (getLeft() != null)
		{
			((WeightedBinaryTree<T>)getLeft()).makeBalances();
		}
		if (getRight() != null)
		{
			((WeightedBinaryTree<T>)getRight()).makeBalances();
		}
	}
	
	
	
	public void balanceOut()
	{
		WeightedBinaryTree<T> wbtree;
		WeightedBinaryTree<T> bestWbtree = this;
		double bestBalance = getBalance();
		
		for (BinaryTree<T> btree : squash())
		{
			wbtree = (WeightedBinaryTree<T>)btree;
			if (wbtree.getBalance() > bestBalance)
			{
				bestBalance = wbtree.getBalance();
				bestWbtree = wbtree;
			}
		}
		
		System.err.println("perfectNode: " + bestWbtree.toString());
	}

	
	
	public WeightedBinaryTree<T> add(T[] _objects)
	{
		for (T object : _objects)
		{
			add(object, false);
		}
		
		((WeightedBinaryTree<T>)getRoot()).makeWeights();
		((WeightedBinaryTree<T>)getRoot()).makeBalances();
		
		return this;
	}
		
	public WeightedBinaryTree<T> add(T _object)
	{
		return add(_object, true);
	}
	
	private WeightedBinaryTree<T> add(T _object, boolean _doBalance)
	{
		super.add(_object);
		
		if (_doBalance)
		{
			incWeight();
			((WeightedBinaryTree<T>)getRoot()).makeBalances();
		}
		return this;
	}
	
	
	
	public WeightedBinaryTree<T> makeInstance(T _object)
	{
		return new WeightedBinaryTree<T>(_object);
	}
	
	protected WeightedBinaryTree<T> makeInstance(T _object, BinaryTree<T> _top)
	{
		WeightedBinaryTree<T> wbtree =  new WeightedBinaryTree<T>(_object);
		wbtree.setTop(_top);
		return wbtree;
	}
	
	
	
	
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb
			.append(super.toString())
			.append(":weight=")
			.append(weight)
			.append(",balance=")
			.append(balance);
		
		return sb.toString();
	}
}
