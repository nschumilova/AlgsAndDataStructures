package com.alds.graph;


import static org.junit.Assert.*;

import org.junit.Test;

public class RouteExistsTest {

    @Test
    public void testRouteNotExistsWhenGraphEmpty() {
        DirectionalGraph<Integer> graph = new DirectionalGraph<>();
        assertFalse(graph.routeExists(1, 2));
    }

    @Test
    public void testRouteNotExistsWhenGraphHasOneNode() {
        DirectionalGraph<Integer> graph = new DirectionalGraph<>();
        graph.addNode(1);
        assertFalse(graph.routeExists(1, 2));
    }

    @Test
    public void testRouteExistsWhenGraphHasOneCyclicNode() {
        DirectionalGraph<Integer> graph = new DirectionalGraph<>();
        graph.addConnection(1,1);
        assertTrue(graph.routeExists(1, 1));
    }

    @Test
    public void testRouteExists() {
        DirectionalGraph<Integer> graph = initGraph();
        assertTrue(graph.routeExists(0,2));
    }

    @Test
    public void testRouteNotExists() {
        DirectionalGraph<Integer> graph = initGraph();
        assertFalse(graph.routeExists(3,0));
    }
    @Test
    public void testRouteNotExistsForAbsentNode() {
        DirectionalGraph<Integer> graph = initGraph();
        assertFalse(graph.routeExists(3,100));
    }

    private static  DirectionalGraph initGraph(){
        DirectionalGraph<Integer> graph = new DirectionalGraph<>();
        graph.addConnection(0,5);
        graph.addConnection(0,4);
        graph.addConnection(0,1);
        graph.addConnection(1,4);
        graph.addConnection(1,3);
        graph.addConnection(3,4);
        graph.addConnection(3,2);
        graph.addConnection(2,1);
        return graph;
    }


}
