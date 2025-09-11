import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {

    static int N;
    static Stair[] stair = new Stair[2];
    static List<Person> people;
    static boolean[] selected; // true면 0번 계단, false면 1번 계단
    static int[][] arrival;
    static int min; // 각 테스트 케이스의 최소 시간을 저장할 변수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testCase; tc++) {
            N = Integer.parseInt(br.readLine());
            people = new ArrayList<>();
            min = Integer.MAX_VALUE; // 테스트 케이스마다 min 초기화

            int temp = 0;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int current = Integer.parseInt(st.nextToken());
                    if (current == 0) continue;
                    if (current == 1) {
                        people.add(new Person(i, j));
                    } else {
                        stair[temp] = new Stair(i, j, current);
                        temp++;
                    }
                }
            }
            selected = new boolean[people.size()];

            // 모든 사람과 계단간의 거리 미리 계산
            arrival = new int[people.size()][2];
            for (int j = 0; j < people.size(); j++) {
                Person p = people.get(j);
                for (int i = 0; i < 2; i++) {
                    int dist = Math.abs(stair[i].r - p.r) + Math.abs(stair[i].c - p.c);
                    arrival[j][i] = dist;
                }
            }

            // 모든 사람의 계단을 배정.
            comb(0);

            System.out.println("#" + tc + " " + min);
        }
    }

    // 백트래킹으로 모든 조합 생성
    static void comb(int idx) {
        if (idx == people.size()) {
            // 그룹 분리 및 시뮬레이션 시작
            List<Integer> groupA_indices = new ArrayList<>(); // 0번 계단 그룹
            List<Integer> groupB_indices = new ArrayList<>(); // 1번 계단 그룹

            for (int i = 0; i < people.size(); i++) {
                if (selected[i]) {
                    groupA_indices.add(i);
                } else {
                    groupB_indices.add(i);
                }
            }

            int time1 = calculateStairTime(groupA_indices, 0);
            int time2 = calculateStairTime(groupB_indices, 1);
            int total = Math.max(time1, time2);

            min = Math.min(min, total);
            return;
        }

        selected[idx] = true; // idx번 사람을 0번 계단으로 선택
        comb(idx + 1);
        selected[idx] = false; // idx번 사람을 1번 계단으로 선택
        comb(idx + 1);
    }

    static int calculateStairTime(List<Integer> group, int stairNum) {
        if (group.isEmpty()) return 0;

        List<Integer> arrivalTimes = new ArrayList<>();
        for (int personIndex : group) {
            arrivalTimes.add(arrival[personIndex][stairNum]);
        }
        Collections.sort(arrivalTimes);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int lastFinishTime = 0;

        for (int arrivalTime : arrivalTimes) {
            int readyTime = arrivalTime + 1;

            if (pq.size() < 3) {
                int finishTime = readyTime + stair[stairNum].k;
                pq.add(finishTime);
            } else { 
                int earliestFinish = pq.poll();
                
                int startTime = Math.max(readyTime, earliestFinish);
                int finishTime = startTime + stair[stairNum].k;
                pq.add(finishTime);
            }
        }
        
        while(!pq.isEmpty()){
            lastFinishTime = pq.poll();
        }
        return lastFinishTime;
    }

    static class Person {
        int r, c;
        Person(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Stair {
        int r, c, k;
        Stair(int r, int c, int k) {
            this.r = r;
            this.c = c;
            this.k = k;
        }
    }
}