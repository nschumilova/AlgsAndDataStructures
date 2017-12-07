package com.alds.graph;


import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProjectsProcessingTest {

    @Test(expected = IllegalArgumentException.class)
    public void testSequenceOfProcessingWhenInputNull() {
        GraphProcessor.constructSequenceOfProcessing(null, null);
    }

    @Test
    public void testSequenceOfProcessingWhenProjectsEmpty() {
        assertTrue(
                GraphProcessor
                        .constructSequenceOfProcessing(new LinkedList<>(), new LinkedList<>())
                        .isEmpty());
    }

    @Test
    public void testSequenceOfProcessingWhenOneProject() {
        List<Character> projects = new LinkedList<>();
        projects.add('a');
        testSequence(projects,new LinkedList<>());
    }

    @Test
    public void testSequenceOfProcessingWhenSeveralDependentProjects() {
        List<Character> projects = new LinkedList<>();
        projects.add('a');
        projects.add('b');
        projects.add('c');
        projects.add('d');
        projects.add('e');
        projects.add('f');
        List<DependencyPair<Character>> dependencies = new LinkedList<>();
        dependencies.add(new DependencyPair<>('a','d'));
        dependencies.add(new DependencyPair<>('f','b'));
        dependencies.add(new DependencyPair<>('b','d'));
        dependencies.add(new DependencyPair<>('f','a'));
        dependencies.add(new DependencyPair<>('d','c'));
        testSequence(projects,dependencies);
    }

    @Test(expected = IllegalStateException.class)
    public void testSequenceOfProcessingWhenCyclicDependencies() {
        List<Character> projects = new LinkedList<>();
        projects.add('a');
        projects.add('b');
        projects.add('c');
        List<DependencyPair<Character>> dependencies = new LinkedList<>();
        dependencies.add(new DependencyPair<>('a','b'));
        dependencies.add(new DependencyPair<>('c','a'));
        dependencies.add(new DependencyPair<>('b','c'));
        GraphProcessor.constructSequenceOfProcessing(projects, dependencies);
    }

    private void testSequence(List<Character> projects,
                              List<DependencyPair<Character>> dependencies) {
        List<Character> result = GraphProcessor.constructSequenceOfProcessing(projects, dependencies);
        assertEquals(projects.size(), result.size());
        for (DependencyPair<Character> dependencyPair : dependencies)
            assertTrue(correctElementSequence(result, dependencyPair.getIndependant(),
                    dependencyPair.getDependant()));
    }


    private boolean correctElementSequence(List<Character> list, Character elBefore, Character elAfter) {
        if (elAfter == null)
            return true;
        int beforePosition = -1, afterPosition = -1;
        int current = 0;
        Iterator<Character> iterator = list.iterator();
        while (iterator.hasNext()) {
            Character next = iterator.next();
            if (next.charValue() == elBefore)
                beforePosition = current;
            else if (next.charValue() == elAfter)
                afterPosition = current;
            if (beforePosition >= 0 && afterPosition >= 0)
                break;
            current++;
        }
        return beforePosition <= afterPosition;
    }

}
