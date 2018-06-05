public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = "images/" + img;
	}
	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = "./images" + p.imgFileName;

	}
	public double calcDistance(Planet p){
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		double distance = Math.sqrt((dx*dx) + (dy*dy));
		return distance;
	}
	public double calcForceExertedBy(Planet p){
		double pairwise_force;
		double g = 6.67 * Math.pow(10, -11);
		double r = calcDistance(p);
		pairwise_force = (g * this.mass * p.mass) / (r*r);
		return pairwise_force;
	}
	public double calcForceExertedByX(Planet p){
		double r = calcDistance(p);
		double dx = p.xxPos - this.xxPos;
		double pforce = calcForceExertedBy(p);
		double pairwise_force_x = pforce * (dx / r);
		return pairwise_force_x;
	}
	public double calcForceExertedByY(Planet p){
		double r = calcDistance(p);
		double dy = p.yyPos - this.yyPos;
		double pforce = calcForceExertedBy(p);
		double pairwise_force_y = pforce * (dy / r);
		return pairwise_force_y;
	}

	public double calcNetForceExertedByX(Planet[] p){
		double net_force_x = 0;
		double each_pairwise_force_x;
		for(Planet each_planet : p){
			if (each_planet.equals(this)){		////Planets cannot exert gravitational forces on themselves
				continue;
			}
			each_pairwise_force_x = calcForceExertedByX(each_planet);
			net_force_x = net_force_x + each_pairwise_force_x;
		}
		return net_force_x;
	}
	public double calcNetForceExertedByY(Planet[] p){
		double net_force_y = 0;
		double each_pairwise_force_y;
		for(Planet each_planet : p){
			if (each_planet.equals(this)){		//Planets cannot exert gravitational forces on themselves
				continue;
			}
			each_pairwise_force_y = calcForceExertedByY(each_planet);
			net_force_y = net_force_y + each_pairwise_force_y;
		}
		return net_force_y;
	}
	public void update(double dt, double fX, double fY){
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		double vX = this.xxVel + (dt * aX);
		double vY = this.yyVel + (dt * aY);
		double pX = this.xxPos + (dt * vX);
		double pY = this.yyPos + (dt * vY);
		this.xxVel = vX;
		this.yyVel = vY;
		this.xxPos = pX;
		this.yyPos = pY;

	}
	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
		StdDraw.show();
	}

}