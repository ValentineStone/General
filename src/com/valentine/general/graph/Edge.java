package com.valentine.general.graph;

public final class Edge
{
	private Edge()
	{}
	
	public double weight;
	
	private int direction;
	
	private Vertex a;
	private Vertex b;
	
	
	
	
	
	
	public static final int FORWARD  = 0b01;
	public static final int BACKWARD = 0b10;
	public static final int BIDIRECT = FORWARD | BACKWARD;
	
	public static Edge makeInstance(Vertex _a, Vertex _b, int _direction, double _weight)
	{
		Edge edge = null;
		
		if
		(
			   _a != null
			&& _b != null
		)
		{
			edge           = new Edge();
			edge.a         = _a;
			edge.b         = _a;
			edge.direction = _direction;
			edge.weight    = _weight;
		}
		
		return edge;
	}
	
	public static Edge makeInstance(Vertex _a, Vertex _b, int _direction)
	{
		return makeInstance(_a, _b, _direction, 0);
	}
}
