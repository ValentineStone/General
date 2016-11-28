package com.valentine.general.graph;

import java.util.*;

public final class Graph
{
	private Graph()
	{}
	
	private List<Vertex> vertices = new ArrayList<>();
	
	
	
	
	
	
	public static Graph makeInstance(double[] _weights, double[][] _edges)
	{
		Graph graph = null;
		
		graph = new Graph();
		
		if (_weights != null)
		{
			for (double weight : _weights)
			{
				graph.vertices.add(Vertex.makeInstance(weight));
			}
		}
		
		if (_weights != null)
		{
			for (double[] edgeData : _edges)
			{
				if (edgeData != null && edgeData.length >= 3)
				{
					if (edgeData.length >= 4)
					{
						Vertex.connect
						(
							graph.vertices.get((int)edgeData[0]),
							graph.vertices.get((int)edgeData[1]),
							(int)edgeData[2],
							edgeData[3]
						);
					}
					else
					{
						Vertex.connect
						(
							graph.vertices.get((int)edgeData[0]),
							graph.vertices.get((int)edgeData[1]),
							(int)edgeData[2]
						);
					}
				}
			}
		}
		
		return graph;
	}
	
}
