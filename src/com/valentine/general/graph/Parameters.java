package com.valentine.general.graph;

public class Parameters
{
	public double weight;
	public double priority;
	
	public void load(Parameters _params)
	{
		weight   = _params.weight;
		priority = _params.priority;
	}
}
