package Order;
import java.util.Random;

/**
 * @author admin
 *�Ŵ��㷨��Լ��
 */
public class GA {

	public static final int varnum=4;  //��������
	public static final double[] lower=new double[varnum]; 
	public static final double[] uper=new double[varnum];  
	public static final int POP_SIZE=200;  //��ʼ��Ⱥ
	public static final double[][] initpop=new double [varnum][POP_SIZE];  //��ʼ��Ⱥ
	public static final int M = 22; //ÿһ����������λ��
	public static String[] pop = new String[POP_SIZE];//��Ⱥ����
	public static double[][] result = new double[varnum][POP_SIZE];//��Ⱥ����Ľ��
	public static final int LENGTH=M * varnum;//���볤�ȣ���ΪҪ��ȷ��С�������λ�����Ա�Ϊ22λ����22*i,iΪ��������
	public static final int MJ2 = 4194304;//2^22
	public static double[] fitness = new double[POP_SIZE];//�����Ⱥ��Ӧ��
	public static final double PC = 0.99;//������
	public static final double PM = 0.2;//������
	public static double[] p = new double[POP_SIZE];//���̶ķ���������Ӧ�ȸ���(����������Ӧ�ȷ���)
	public static double[] q=new double[POP_SIZE];//q[i]��ǰn��p֮�ͣ��ۻ����ʣ�
	public static Random random=new Random();//���ڲ���������Ĺ���
	public static Best best=new Best();//��¼��Ѵ𰸵Ķ���

	public void encoding() //����
	{
		for (int i = 0; i < POP_SIZE; i++) {
			pop[i]="";
			for(int j=0;j<varnum;j++){
				String GeneCode1=Integer.toBinaryString(3);
				double d1=((initpop[j][i]-lower[j])/(uper[j]-lower[j]))*(MJ2-1);
				String GeneCode=Integer.toBinaryString((int)d1);
				if(GeneCode.length()<M){
					int k=M-GeneCode.length();
					for(int l=0;l<k;l++){ //���Ȳ��㲹��
						GeneCode="0"+GeneCode;
					}
				}
				pop[i] += GeneCode; //�����յı������pop[i]
			}
		}
	}
	
	public void decoding()//���룬��2���Ʊ���ת��Ϊ10����
	{
		for (int i = 0; i < pop.length; i++) {
			for(int j=0;j<varnum;j++){
				int k = Integer.parseInt((pop[i].substring(j*22, (j+1)*22)), 2); //ע�������е�ֵ������
				if(j==1 || j==3){
					result[j][i] = lower[j]+k*(uper[j]-lower[j])/(MJ2-1);
					result[j][i] = (int)result[j][i];
					//System.out.print("��ӡ����");
					//System.out.print(result[j][i]);
				}else{
					result[j][i]=lower[j]+k*(uper[j]-lower[j])/(MJ2-1);
					//System.out.print("��ӡ����");
					//System.out.print(result[j][i]);
				}
			}
		}
	}
	
	public void fitness()
	{
		for (int i = 0; i < POP_SIZE; i++) {
			fitness[i] = 1000;
			double a = 127 - 2*result[0][i]*result[0][i] - 3*result[1][i]*result[1][i]*result[1][i]*result[1][i] - result[2][i] - 4*result[3][i]*result[3][i];
			if(a>=0){
				fitness[i]= 100000 - ((result[0][i]-10)*(result[0][i]-10) + 5*(result[1][i]-12)*(result[1][i]-12) + result[2][i]*result[2][i]*result[2][i]*result[2][i] + 3*(result[3][i]-11)*(result[3][i]-11));
			}
			//System.out.print("��ӡ��ֵ" + i + " ");
			//System.out.print(fitness[i]);
		}
	 }
	
	public void crossover(){//���㽻��
		for(int i=0;i<POP_SIZE;i++){
			double d=random.nextDouble();
			if(d<PC){
				int k1=random.nextInt(POP_SIZE);
				int k2=random.nextInt(POP_SIZE);
				int position=random.nextInt(LENGTH);
				String s11="",s12="",s21="",s22="";
				s11=pop[k1].substring(0,position);
				s12=pop[k1].substring(position,LENGTH);
				s21=pop[k2].substring(0,position);
				s22=pop[k2].substring(position, LENGTH);
				pop[k1]=s11+s22;
				pop[k2]=s21+s12;
			}
		}
	}
	
	public void mutation() //����
	{
		for (int i = 0; i < pop.length; i++) {
			for (int j = 0; j < LENGTH; j++) {
				double k=random.nextDouble();
				if(PM>k)
				{
					mutation(i,j);
				}
			}
		}
	}
	
	public void mutation(int i,int j) //����
	{
		String s=pop[i];
		StringBuffer sb=new StringBuffer(s);
		if(sb.charAt(j)=='0')
			sb.setCharAt(j, '1');
		else
			sb.setCharAt(j, '0');
		pop[i]=sb.toString();
	}
	
	public void roulettewheel()
	{
		decoding();
		fitness();
		
		double sum=0;
		for (int i = 0; i <POP_SIZE; i++) {
			sum=fitness[i]+sum;
		}
		for (int i = 0; i < POP_SIZE; i++) {
			p[i]=fitness[i]/sum;
			q[i]=0;
	    }
		for (int i = 0; i < POP_SIZE; i++) {
			for (int j = 0; j <= i; j++) {
				q[i]+=p[j];
			}
		}
		
		double[] ran = new double[POP_SIZE];
		String[] tempPop=new String[POP_SIZE];
		for (int i = 0; i < ran.length; i++) {
			ran[i]=random.nextDouble();
		}
		for (int i = 0; i < ran.length; i++) {
			int k = 0;
			for (int j = 0; j < q.length; j++) {
				if(ran[i]<q[j]){
					k=j;
					break;
				}
				else continue;
			}
			tempPop[i]=pop[k];
	   }
		
	   for (int i = 0; i < tempPop.length; i++) {
		   pop[i]=tempPop[i];
		   //System.out.print("�����Ⱥ��");
		   //System.out.print(pop[i] + "  ");
	   }
	}
	
	public void evolution()  //����
	{
		encoding();
		crossover();
		mutation();
		decoding();
		fitness();
		roulettewheel();//���̶�
		findResult();
	}
	
	public void dispose(int n)  //�Խ������е���
	{
		for (int i = 0; i < n; i++) {
			evolution();
			System.out.println("��" + i + "�ε�����");
	   }
	}
	
	public double findResult()
	{
		if(best == null) best=new Best();
		double max = best.fitness;
		for (int i = 0; i < fitness.length; i++) {
		    if(fitness[i] >= max)
		    {
		    	best.fitness = fitness[i];
		    	for(int m=0;m<varnum;m++){
		    		best.x[m]=result[m][i];
		    	}
		    	best.str = pop[i];
		    }
		}
		return max;
	}

//	public static void main(String[] args) {
//		//dΪ������Ⱥ
//				lower[0] = 0;
//				uper[0] = 8.28;
//				lower[1] = -10;
//				uper[1] = 10;
//				lower[2] = -10;
//				uper[2] = 10;
//				lower[3] = -50;
//				uper[3] = 50;
//				
//				//��ʼ����Ⱥ
//				for(int i=0;i<varnum;i++){
//					for(int j=0;j<POP_SIZE;j++){
//						result[i][j]=lower[i]+random.nextDouble()*(uper[i]-lower[i]);
//					}
//				}
//				
//				//��ʼ����������
//				GA ga = new GA();
//				
//				//�������������10000��
//				long starttime=System.currentTimeMillis();
//				ga.dispose(10000);
//				long endtime=System.currentTimeMillis();
//				System.out.println("������ʱ��"+(endtime-starttime)+"ms");
//				System.out.println("���Ϊ��");
//				for(int i=0;i<varnum;i++){
//					System.out.print("x["+(i+1)+"]="+best.x[i]+"t");
//				}
//				System.out.println();
//				System.out.println("Լ������1��ֵ��"+(127 - 2*best.x[0]*best.x[0] - 3*best.x[1]*best.x[1]*best.x[1]*best.x[1] - best.x[2] - 4*best.x[3]*best.x[3]));
//				System.out.println("Ŀ�꺯��ֵ��" + ((best.x[0]-10)*(best.x[0]-10) + 5*(best.x[1]-12)*(best.x[1]-12) + best.x[2]*best.x[2]*best.x[2]*best.x[2] + 3*(best.x[3]-11)*(best.x[3]-11)));
//				System.out.println("Function="+(100000 - best.fitness));
//
//	}
}
