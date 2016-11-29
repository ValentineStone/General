package com.valentine.general.graph;

import java.io.*;
import java.util.*;

import com.google.gson.*;
import com.valentine.general.graph.Graph.*;

public final class Graph
{
	private Graph()
	{}
	
	private List<Vertex> vertices = new ArrayList<>();
	
	
	
	
	public static final class Vertex implements Iterable<Edge>
	{
		public final Parameters params;
		
		private List<Edge> edges;

		private Vertex()
		{
			params = new Parameters();
			edges = new ArrayList<>();
		}
		
		private void freeze()
		{
			edges = Collections.unmodifiableList(edges);
		}

		public Iterator<Edge> iterator()
		{
			return edges.iterator();
		}
		
	}
	
	public static final class Edge
	{
		
		private Edge(Vertex _a, Vertex _b, Direction _direction)
		{
			params = new Parameters();
			a = _a;
			b = _b;
			direction = _direction;
			
			a.edges.add(this);
			b.edges.add(this);
		}
		
		public final Direction direction;
		
		public final Parameters params;
		
		public final Vertex a;
		public final Vertex b;
		
		
		public static enum Direction
		{
			FORWARD,
			BACKWARD,
			BIDIRECT
		}
	}

	
	
	
	
	private static final class GraphDataJson
	{
		private GraphDataJson(File _file) throws FileNotFoundException
		{
			Gson gson = new Gson();
			Reader reader = new FileReader(_file);
			GraphDataJson data = gson.fromJson(reader, GraphDataJson.class);
			edges = data.edges;
			vertices = data.vertices;
		}
		
		private static final class EdgeDataJson
		{
			Parameters params;
			int a;
			int b;
			boolean forward;
			boolean backward;
		}
		
		private static final class VertexDataJson
		{
			Parameters params;
		}
		
		List<EdgeDataJson> edges;
		List<VertexDataJson> vertices;
	}
	
}
