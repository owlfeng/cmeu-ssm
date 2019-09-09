package util;

import java.util.ArrayList;
import java.util.Set;

public class test {

    public static void main(String[] args) {
        float min_dis = Integer.MAX_VALUE;
        System.out.println(min_dis);
        ArrayList<float[]> dataSet = new ArrayList<float[]>();

        dataSet.add(new float[]{1, 1, 2, 3, 1});
        dataSet.add(new float[]{2, 3, 3, 3, 2});
        dataSet.add(new float[]{3, 3, 4, 4, 3});
        dataSet.add(new float[]{4, 5, 6, 5, 4});
        dataSet.add(new float[]{5, 8, 9, 6, 5});
        dataSet.add(new float[]{6, 4, 5, 4, 6});
        dataSet.add(new float[]{7, 6, 4, 2, 7});
        dataSet.add(new float[]{8, 3, 9, 7, 8});
        dataSet.add(new float[]{9, 5, 9, 8, 9});
        dataSet.add(new float[]{10, 4, 2, 10, 10});
        dataSet.add(new float[]{11, 1, 9, 12, 11});
        dataSet.add(new float[]{12, 7, 8, 112, 12});
        dataSet.add(new float[]{13, 7, 8, 4, 13});

        KMeansRun kRun = new KMeansRun(4, dataSet);

        Set<Cluster> clusterSet = kRun.run();
        System.out.println("单次迭代次数 ：" + kRun.getIterTimes());
        for (Cluster cluster : clusterSet) {
            System.out.println(cluster);
        }
    }
}
