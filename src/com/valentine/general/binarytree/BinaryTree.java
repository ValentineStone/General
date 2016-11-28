package com.valentine.general.binarytree;

import java.util.*;

public class BinaryTree<T extends Comparable<T>> implements Iterable<BinaryTree<T>>
{
	private T object = null;
	
	protected int count = 0;
	
	public abstract class Compare
	{
		private Compare(){}
		public static final int LESS = -1;
		public static final int GREATER = 1;
		public static final int EQUAL = 0;
	}
	
	public enum Side
	{
		TOP,
		LEFT,
		RIGHT
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
	
	private BinaryTree(T _object, BinaryTree<T> _top)
	{
		this(_object, 1, _top);
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
		if (count == 0)
		{
			object = _object;
			count = 1;
			return this;
		}
		
		switch (compare(object, _object))
		{
			case Compare.GREATER:
				if (right == null)
					right = makeInstance(_object, this);
				else
					right.add(_object);
				break;
			case Compare.LESS:
				if (left == null)
					left = makeInstance(_object, this);
				else
					left.add(_object);
				break;
			case Compare.EQUAL:
				count++;
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
	
	
	
	
	
	
	
	public BinaryTree<T> makeInstance(T _object)
	{
		return new BinaryTree<T>(_object);
	}
	
	protected BinaryTree<T> makeInstance(T _object, BinaryTree<T> _top)
	{
		return new BinaryTree<T>(_object, _top);
	}
	
	protected void setTop(BinaryTree<T> _top)
	{
		top = _top;
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
	
	
	
	public List<BinaryTree<T>> squash()
	{
		return squash(new ArrayList<>());
	}
	
	protected List<BinaryTree<T>> squash(List<BinaryTree<T>> _list)
	{
		if (left != null)
		{
			left.squash(_list);
		}
		
		_list.add(this);
		
		if (right != null)
		{
			right.squash(_list);
		}
		
		return _list;
	}
	
	
	
	public Iterator<BinaryTree<T>> iterator()
	{
		return squash().iterator();
	}
	
	/*
	public Iterator<BinaryTree<T>> iterator()
	{
		return new Iterator<BinaryTree<T>>()
		{
			private BinaryTree<T> currentNode = left != null ? left : right;
			
			private Side side = Side.LEFT;
			
			private Iterator<BinaryTree<T>> leftIterator;
			private Iterator<BinaryTree<T>> rightIterator;
			
			{
				if (left != null)
				{
					leftIterator = left.iterator();
				}
				else
				{
					leftIterator = Collections.emptyIterator();
				}
				
				if (right != null)
				{
					rightIterator = left.iterator();
				}
				else
				{
					rightIterator = Collections.emptyIterator();
				}
			}
			
			public boolean hasNext()
			{
				switch (side)
				{
					case LEFT:
						return leftIterator.hasNext() || rightIterator.hasNext();
					case RIGHT:
						return rightIterator.hasNext();
					default:
						return false;
				}
			}

			public BinaryTree<T> next()
			{
				switch (side)
				{
					case LEFT:
						if (left != null && (leftIterator = left.iterator()).hasNext())
						{
							return leftIterator.next();
						}
					case RIGHT:
						return right;
					default:
						return null;
				}
			}
		};
	}
	*/
}
