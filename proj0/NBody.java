import java.math.*;
public class NBody{
	public static double readRadius(String path){
		In opened_file = new In(path);
		int planet_num = opened_file.readInt();		//everytime you read a object of class in, it assumes that it reads the next item from the file, with specific type 
		double radius = opened_file.readDouble();
		return radius;
	}
	public static Planet[] readPlanets(String path){
		int i = 0;
		In opened_file = new In(path);
		int planet_num = opened_file.readInt();	
		System.out.println("planet obejects number is " + planet_num);
		Planet[] planets_data = new Planet[planet_num];		//声明并分配一段Planet类型的数组空间，planets_data指向这段数组空间的首地址
		System.out.println("radius is " + opened_file.readDouble());
		while(!opened_file.isEmpty()){
			double read_xP = opened_file.readDouble();
			double read_yP = opened_file.readDouble();
			double read_xV = opened_file.readDouble();
			double read_yV = opened_file.readDouble();
			double read_mass = opened_file.readDouble();
			String read_imgFileName = opened_file.readString();
			planets_data[i] = new Planet(read_xP, read_yP, read_xV, read_yV, read_mass, read_imgFileName); 
			/*planets_data[i].imgFileName = opened_file.readString();
			此代码出现空指针错的原因在于，尽管当前planets_data[]数组的第一个数据空间是planet类型并且存在，但此处并没有存放实例化的对象
			所以想要reference到一个不存在的对象的实例变量肯定会出现错误；
			vtable与变量的空间存在的前提是该类已经实例化成为object
			并且有被reference（这里是数组planets_data[i]）才不会被garbage collection回收掉
			*/

			i ++;
		}
		return planets_data;		//已初始化过的Planet object array;
	}
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];		//filename is data/planets.txt typing from command line
		double r = readRadius(filename);
		Planet[] p_list = readPlanets(filename);		//initialized Planets object array
		String bgp = "./images/starfield.jpg" ;


		double[] xForces = new double[p_list.length];
		double[] yFofces = new double[p_list.length];		//netforce for each Planet object
		for(double time = 0; time != T; time = time +dt){
			StdDraw.enableDoubleBuffering();

			for(int i = 0; i < p_list.length; i++){
				xForces[i] = p_list[i].calcNetForceExertedByX(p_list);
				yFofces[i] = p_list[i].calcNetForceExertedByY(p_list);
			}
			/*通过当前各个planet object的位置计算并存放每个object的netforce后，再进行update。
			如果每计算完一个合力便update的话会通过p_list影响后面后待计算netforce的值*/
			for(int j = 0; j < p_list.length; j++){
				p_list[j].update(dt, xForces[j], yFofces[j]);
			}

			StdDraw.setScale(-4*(r), 4*(r));
			StdDraw.picture(-100, 100, bgp);
			for(Planet each_planet : p_list){
				StdDraw.picture(each_planet.xxPos, each_planet.yyPos, each_planet.imgFileName);
			}
			StdDraw.show();
	
		}
		StdOut.printf("%d\n", p_list.length);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < p_list.length; i++) {
   		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   			p_list[i].xxPos, p_list[i].yyPos, p_list[i].xxVel,
   			p_list[i].yyVel, p_list[i].mass, p_list[i].imgFileName);   
}

	}
}