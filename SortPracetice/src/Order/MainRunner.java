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
	 * ð������� ������ÿ���ٱ�һ��
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
		System.out.println("ʱ�临�Ӷ�Ϊ��" + time);
		return arr;
	}

	/**
	 * ѡ������
	 * 
	 * @param arraySource
	 * @return
	 */
	private int[] sortByChoice(int[] arraySource) {
		int[] arr = Arrays.copyOf(arraySource, arraySource.length); // ���Ƴ�һ�ݷ�������
		int time = 0; // ����ʱ�临�Ӷ�
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
		System.out.println("ʱ�临�Ӷ�Ϊ��" + time);
		return arr;
	}

	/**
	 * ��������
	 * 
	 * @param arraySource
	 * @return
	 */
	private int[] sortByInsert(int[] arraySource) {
		int[] arr = Arrays.copyOf(arraySource, arraySource.length); // ���Ƴ�һ�ݷ�������
		int time = 0; // ����ʱ�临�Ӷ�
		for (int i = 1; i < arr.length; i++) {
			// ��¼Ҫ���������
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
		System.out.println("ʱ�临�Ӷ�Ϊ��" + time);
		return arr;
	}

	/**
	 * ϣ�����򣬻��ڲ���������Խϴ��ģ������Ч�ʻ��һЩ��
	 * @param arraySource
	 * @return
	 */
	private int[] sortByHill(int[] arraySource) {
		int[] arr = Arrays.copyOf(arraySource, arraySource.length); // ���Ƴ�һ�ݷ�������
		int time = 0; // ����ʱ�临�Ӷ�
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
		System.out.println("ʱ�临�Ӷ�Ϊ��" + time);
		return arr;
	}

	/**
	 *�鲢����鲢�����ϵ�һ����Ч�������㷨�����㷨�ǲ��÷��η���Divide and Conquer����һ���ǳ����͵�Ӧ�á�
��Ϊһ�ֵ��͵ķֶ���֮˼����㷨Ӧ�ã��鲢�����ʵ�������ַ�����
	 * @param arraySource
	 * @return
	 */
	private int[] sortByMerge(int[] arraySource) {
		int[] arr = Arrays.copyOf(arraySource, arraySource.length); // ���Ƴ�һ�ݷ�������
		int time = 0; // ����ʱ�临�Ӷ�
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
		System.out.println("ʱ�临�Ӷ�Ϊ��" + time);
		return arr;
	}
	
	
}
