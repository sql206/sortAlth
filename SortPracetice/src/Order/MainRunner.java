package Order;

import java.util.Arrays;

public class MainRunner {

/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// int[] nums = { 1, 5, 6, 3, 4, 7, 2, 9, 8 };
		int[] nums = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		MainRunner mainRunner = new MainRunner();
		// int[] result = mainRunner.sortByMaoPao(nums);
		int[] result = mainRunner.sortByHill(nums);
		for (int i : result) {
			System.out.println(i + "   ");
		}
	
	}*/

	/**
	 * 冒泡排序大 的往后每次少比一次
	 * 
	 * @param arraySource
	 * @return
	 */
	private int[] sortByMaoPao(int[] arraySource) {
		int[] arr = Arrays.copyOf(arraySource, arraySource.length);
		int time = 0;
		for (int i = 1; i < arr.length; i++) {
			boolean flag = true;
			for (int j = 0; j < arr.length - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int tep = arr[j];
					time += 1;
					arr[j] = arr[j + 1];
					time += 1;
					arr[j + 1] = tep;
					time += 1;
					flag = false;
				}
				// time += 3;
			}
			if (flag) {
				break;
			}
		}
		System.out.println("时间复杂度为：" + time);
		return arr;
	}

	/**
	 * 选择排序
	 * 
	 * @param arraySource
	 * @return
	 */
	private int[] sortByChoice(int[] arraySource) {
		int[] arr = Arrays.copyOf(arraySource, arraySource.length); // 复制出一份放在外面
		int time = 0; // 计算时间复杂度
		for (int i = 0; i < arr.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				time += 1;
				if (arr[j] < arr[min]) {
					min = j;
					time += 1;
				}
			}
			if (i != min) {
				int temp = arr[i];
				time += 1;
				arr[i] = arr[min];
				time += 1;
				arr[min] = temp;
				time += 1;
			}
		}
		System.out.println("时间复杂度为：" + time);
		return arr;
	}

	/**
	 * 插入排序
	 * 
	 * @param arraySource
	 * @return
	 */
	private int[] sortByInsert(int[] arraySource) {
		int[] arr = Arrays.copyOf(arraySource, arraySource.length); // 复制出一份放在外面
		int time = 0; // 计算时间复杂度
		for (int i = 1; i < arr.length; i++) {
			// 记录要插入的数据
			int temp = arr[i];
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] > temp) {
					arr[j + 1] = arr[j];
					if (j == 0) {
						arr[j] = temp;
					}
				} else {
					arr[j] = temp;
				}
			}
		}
		System.out.println("时间复杂度为：" + time);
		return arr;
	}

	/**
	 * 希尔排序，基于插入排序，针对较大规模的数据效率会高一些。
	 * @param arraySource
	 * @return
	 */
	private int[] sortByHill(int[] arraySource) {
		int[] arr = Arrays.copyOf(arraySource, arraySource.length); // 复制出一份放在外面
		int time = 0; // 计算时间复杂度
		int gap = 1;		
		while (gap < arr.length) {
			gap = gap * 3 + 1;
		}
		while (gap > 0) {
			for(int i=gap;i<arr.length;i++) {
				int temp=arr[i];
				int j=i-gap;
				while(j>=0&&arr[j]>temp) {
					arr[j+gap]=arr[j];
					j-=gap;
				}
				arr[j+gap]=temp;
			}
			gap=(int)Math.floor(gap/3);
		}
		System.out.println("时间复杂度为：" + time);
		return arr;
	}

	/**
	 *归并排序归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
作为一种典型的分而治之思想的算法应用，归并排序的实现由两种方法：
	 * @param arraySource
	 * @return
	 */
	private int[] sortByMerge(int[] arraySource) {
		int[] arr = Arrays.copyOf(arraySource, arraySource.length); // 复制出一份放在外面
		int time = 0; // 计算时间复杂度
		int gap = 1;		
		while (gap < arr.length) {
			gap = gap * 3 + 1;
		}
		while (gap > 0) {
			for(int i=gap;i<arr.length;i++) {
				int temp=arr[i];
				int j=i-gap;
				while(j>=0&&arr[j]>temp) {
					arr[j+gap]=arr[j];
					j-=gap;
				}
				arr[j+gap]=temp;
			}
			gap=(int)Math.floor(gap/3);
		}
		System.out.println("时间复杂度为：" + time);
		return arr;
	}
	
	
}
