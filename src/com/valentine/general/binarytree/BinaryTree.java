package com.valentine.general.binarytree;

import java.util.*;

public class BinaryTree<T extends Comparable<T>> implements Iterable<BinaryTree<T>>, Comparable<BinaryTree<T>>
{
	private T object = null;
	
	protected int count = 0;
	private int weight = 1;
	private double balance = 0;
	
	public final class Compare
	{
		private Compare(){}
		public static final int LESS = -1;
		public static final int GREATER = 1;
		public static final int EQUAL = 0;
	}
	
	private BinaryTree<T> top = null;
	private BinaryTree<T> left = null;
	private BinaryTree<T> right = null;
	
	
	
	public BinaryTree()
	{
		this(null, 0, null);
	}
	
	public BinaryTree(T _object)
	{
		this(_object, 1, null);
	}
	
	public BinaryTree(T[] _objects)
	{
		this
		(
			(_objects != null && _objects.length >  0) ? _objects[0] : null,
			(_objects == null || _objects.length == 0) ? 0 : 1,
			null
		);
		
		if (object != null)
		{
			for (int i = 1; i < _objects.length; i++)
			{
				add(_objects[i]);
			}
		}
	}
	
	private BinaryTree(T _object, int _count, BinaryTree<T> _top)
	{
		top = _top;
		add(_object);
	}
	
	
	
	public int getCount()
	{
		return count;
	}
	
	public int getWeight()
	{
		return weight;
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
			weight;
		
		up =
			getRoot().weight
			- weight
			+ count;
		
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
	
	public void makeBalances()
	{
		makeBalance();
		
		if (getLeft() != null)
		{
			getLeft().makeBalances();
		}
		if (getRight() != null)
		{
			getRight().makeBalances();
		}
	}
	
	
	
	public BinaryTree<T> balanceOut()
	{
		makeBalances();
		
		TreeSet<BinaryTree<T>> treeSet =
			new TreeSet<>(squash(true));
		
		BinaryTree<T> high = treeSet.first();
		treeSet.remove(high);
		
		for (BinaryTree<T> low : treeSet)
			high.add(low);
		
		high.makeBalances();
		
		TreeSet<BinaryTree<T>> treeSetRebalanced =
			new TreeSet<>(high.squash(false));
		treeSetRebalanced.remove(treeSetRebalanced.first());
		
		
		if (!treeSetRebalanced.equals(treeSet))
		{
			System.err.println("NEQ ");
			high = high.balanceOut();
		}
		
		return high;
	}
	
	
	

	public BinaryTree<T> getTop()
	{
		return top;
	}

	public BinaryTree<T> getLeft()
	{
		return left;
	}

	public BinaryTree<T> getRight()
	{
		return right;
	}
	
	public BinaryTree<T> getRoot()
	{
		BinaryTree<T> root;
		if (top == null)
		{
			root = this;
			return this;
		}
		else
		{
			root = top.getRoot();
		}
		return root;
	}
	
	
	
	public BinaryTree<T> add(T[] _objects)
	{
		for (T object : _objects)
		{
			add(object);
		}
		return this;
	}

	public BinaryTree<T> add(T _object)
	{
		weight++;
		
		if (count == 0)
		{
			object = _object;
			count = 1;
			weight = 1;
			return this;
		}
		
		switch (compare(object, _object))
		{
			case Compare.GREATER:
				if (right == null)
					right = makeInstance(_object, 1, this);
				else
					right.add(_object);
				break;
			case Compare.LESS:
				if (left == null)
					left = makeInstance(_object, 1, this);
				else
					left.add(_object);
				break;
			case Compare.EQUAL:
				count++;
		}
		
		return this;
	}
	
	public BinaryTree<T> add(BinaryTree<T> _tree)
	{
		weight += _tree.weight;
		_tree.top = this;
		
		switch (compare(object, _tree.object))
		{
			case Compare.GREATER:
				if (right == null)
					right = _tree;
				else
					right.add(_tree);
				break;
			case Compare.LESS:
				if (left == null)
					left = _tree;
				else
					left.add(_tree);
				break;
		}
		
		return this;
	}
	
	
	
	
	public int compare(T _obj1, T _obj2)
	{
		if (_obj1 == null || _obj2 == null)
		{
			if (_obj1 == _obj2)
				return Compare.EQUAL;
			if (_obj1 == null)
				return Compare.LESS;
			if (_obj2 == null)
				return Compare.GREATER;
		}
		
		int compareRes = _obj1.compareTo(_obj2);
		
		if (compareRes > 0)
			return Compare.GREATER;
		if (compareRes < 0)
			return Compare.LESS;
		else
			return Compare.EQUAL;
	}
	
	
	
	protected BinaryTree<T> makeInstance(T _object, int _count, BinaryTree<T> _top)
	{
		return new BinaryTree<T>(_object, _count, _top);
	}
	
	
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb
			.append('[')
			.append(this.getClass().getSimpleName())
			.append('(')
			.append(count);
		
		if (object == null)
		{
			sb.append(",null)]");
		}
		else
		{
			sb
			.append(')')
			.append(object.toString())
			.append(']');
		}
		
		sb
			.append(":weight=")
			.append(weight)
			.append(",balance=")
			.append(balance);
		
		return sb.toString();
	}
	
	
	
	
	
	
	
	
	private static String indentationString = ".\t";
	
	public static String getIndentationChar()
	{
		return indentationString;
	}

	public static void setIndentationChar(String _indentationString)
	{
		indentationString = _indentationString;
	}
	
	
	
	
	
	

	public String toText()
	{
		return toText("");
	}
	
	private String toText(String _indentationString)
	{
		String nextIndentationString = _indentationString + indentationString;
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder
			.append(this);
		
		if (left != null || right != null)
		{
			stringBuilder
				.append('\n')
				.append(_indentationString)
				.append('{');
		
			if (left != null)
			{
				stringBuilder
					.append('\n')
					.append(nextIndentationString)
					.append("l:")
					.append(left.toText(nextIndentationString));
			}
			if (right != null)
			{
				stringBuilder
				.append('\n')
				.append(nextIndentationString)
				.append("r:")
				.append(right.toText(nextIndentationString));
			}
			
			stringBuilder
				.append('\n')
				.append(_indentationString)
				.append("}");
		}
		
		return stringBuilder.toString();
	}
	
	
	
	public List<BinaryTree<T>> squash(boolean _clear)
	{
		return squash(new ArrayList<>(), _clear);
	}
	
	protected List<BinaryTree<T>> squash(List<BinaryTree<T>> _list, boolean _clear)
	{
		if (left != null)
		{
			left.squash(_list, _clear);
		}
		
		_list.add(this);
		
		if (right != null)
		{
			right.squash(_list, _clear);
		}
		
		if (_clear)
		{
			left = null;
			right = null;
			top = null;
			weight = count;
		}
		
		return _list;
	}
	
	
	
	public Iterator<BinaryTree<T>> iterator()
	{
		return squash(false).iterator();
	}
	
	

	public int compareTo(BinaryTree<T> _o)
	{
		return -Double.compare(balance, _o.balance);
	}
}
