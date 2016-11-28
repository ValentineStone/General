package com.valentine.general.graph;

import java.util.*;

public final class Vertex
{
	private Vertex()
	{}
	
	public double weight;
	
	public List<Edge> edges = new ArrayList<>();
	
	
	
	
	
	
	
	
	public static Vertex makeInstance(double _weight)
	{
		Vertex vertex = null;
		
		vertex        = new Vertex();
		vertex.weight = _weight;
		
		return vertex;
	}
	
	public static Vertex makeInstance()
	{
		return makeInstance(0);
	}
	
	
	
	public static void connect(Vertex _a, Vertex _b, int _direction, double _weight)
	{
		Edge edge = Edge.makeInstance(_a, _b, _direction, _weight);
		_a.edges.add(edge);
		_b.edges.add(edge);
	}
	
	public static void connect(Vertex _a, Vertex _b, int _direction)
	{
		connect(_a, _b, _direction, 0);
	}
}


