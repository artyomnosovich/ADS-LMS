package by.it.group351051.nosovich.lesson13;

import java.util.*;

public class GraphA {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();

        String[] edges = input.split(", ");
        for (String edge : edges) {
            String[] parts = edge.split(" -> ");
            String u = parts[0];
            String v = parts[1];

            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(v);

            inDegree.put(u, inDegree.getOrDefault(u, 0));
            inDegree.put(v, inDegree.getOrDefault(v, 0) + 1);
        }

        topologicalSort(graph, inDegree);
    }

    public static void topologicalSort(Map<String, List<String>> graph, Map<String, Integer> inDegree) {
        PriorityQueue<String> queue = new PriorityQueue<>();

        for (String node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                queue.offer(node);
            }
        }

        List<String> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            String current = queue.poll();
            result.add(current);

            if (graph.containsKey(current)) {
                for (String neighbor : graph.get(current)) {
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                    if (inDegree.get(neighbor) == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        for (String node : result) {
            System.out.print(node + " ");
        }
    }
}
