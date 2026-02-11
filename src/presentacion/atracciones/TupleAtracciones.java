package presentacion.atracciones;

public class TupleAtracciones<A,B,C> extends Object { 
	
	private A a; 
	private B b; 
	private C c;
	
	public TupleAtracciones(A a, B b, C c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
	public A first() { return a; }
	public B second() { return b; }
	public C third() { return c; }
}
