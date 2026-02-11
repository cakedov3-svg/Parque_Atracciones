package presentacion.controlador;

public class EventosControlador {

	
		//EVENTOS SISTEMA: 0XX
	public static final int SISTEMA_PANTALLA_INICIO = 000;
		
		//EVENTOS SUBSISTEMA TRABAJADORES: 1XX
	public static final int TRABAJADOR_CREAR_OK = 100;
	public static final int TRABAJADOR_CREAR_ERROR = 101; 
	public static final int TRABAJADOR_CREAR = 102;
	
	public static final int TRABAJADOR_ELIMINAR_OK = 103;
	public static final int TRABAJADOR_ELIMINAR_ERROR = 104;
	public static final int TRABAJADOR_ELIMINAR = 105;
	
	public static final int TRABAJADOR_BUSCAR_OK = 106;
	public static final int TRABAJADOR_BUSCAR_ERROR = 107;
	public static final int TRABAJADOR_BUSCAR = 108;
	
	public static final int TRABAJADOR_MODIFICAR_OK = 109;
	public static final int TRABAJADOR_MODIFICAR_ERROR = 110;
	public static final int TRABAJADOR_MODIFICAR = 111;
	
	public static final int TRABAJADOR_LISTAR_OK = 112;
	public static final int TRABAJADOR_LISTAR_ERROR = 113;
	public static final int TRABAJADOR_LISTAR = 114;
	
	public static final int TRABAJADOR_VISTA_CREAR = 115;
	public static final int TRABAJADOR_VISTA_ELIMINAR = 116;
	public static final int TRABAJADOR_VISTA_MODIFICAR = 117;
	public static final int TRABAJADOR_VISTA_BUSCAR = 118;
	public static final int TRABAJADOR_VISTA_LISTAR = 119;
	public static final int TRABAJADOR_VISTA_INICIO = 120;
	
	public static final int TRABAJADOR_CREAR_CANCEL = 121;
	public static final int TRABAJADOR_ELIMINAR_CANCEL = 122;
	public static final int TRABAJADOR_MODIFICAR_CANCEL = 123;
		
		//EVENTOS SUBSISTEMA DEPARTAMENTOS: 2XX
	public static final int DEPARTAMENTOS_CREAR_OK = 200;
	public static final int DEPARTAMENTOS_CREAR_ERROR = 201;
	public static final int DEPARTAMENTOS_CREAR = 202;
	public static final int DEPARTAMENTOS_ELIMINAR_OK = 203;
	public static final int DEPARTAMENTOS_ELIMINAR_ERROR = 204;
	public static final int DEPARTAMENTOS_ELIMINAR = 205;
	public static final int DEPARTAMENTOS_BUSCAR_OK = 206;
	public static final int DEPARTAMENTOS_BUSCAR_ERROR = 207;
	public static final int DEPARTAMENTOS_BUSCAR = 208;
	public static final int DEPARTAMENTOS_MODIFICAR_OK = 209;
	public static final int DEPARTAMENTOS_MODIFICAR_ERROR = 210;
	public static final int DEPARTAMENTOS_MODIFICAR = 211;
	public static final int DEPARTAMENTOS_LISTAR_OK = 212;
	public static final int DEPARTAMENTOS_LISTAR_ERROR = 213;
	public static final int DEPARTAMENTOS_LISTAR = 214;
	public static final int DEPARTAMENTOS_VISTA_CREAR = 215;
	public static final int DEPARTAMENTOS_VISTA_ELIMINAR = 216;
	public static final int DEPARTAMENTOS_VISTA_MODIFICAR = 217;
	public static final int DEPARTAMENTOS_VISTA_BUSCAR = 218;
	public static final int DEPARTAMENTOS_VISTA_LISTAR = 219;
	public static final int DEPARTAMENTOS_VISTA_INICIO = 220;
		
		
		//EVENTOS SUBSISTEMA CLIENTES: 3XX
	public static final int CLIENTE_CREAR_OK = 300;
	public static final int CLIENTE_CREAR_ERROR = 301; 
	public static final int CLIENTE_CREAR = 302;
	
	public static final int CLIENTE_ELIMINAR_OK = 3303;
	public static final int CLIENTE_ELIMINAR_ERROR = 304;
	public static final int CLIENTE_ELIMINAR = 305;
	
	public static final int CLIENTE_BUSCAR_OK = 306;
	public static final int CLIENTE_BUSCAR_ERROR = 307;
	public static final int CLIENTE_BUSCAR = 308;
	
	public static final int CLIENTE_MODIFICAR_OK = 309;
	public static final int CLIENTE_MODIFICAR_ERROR = 310;
	public static final int CLIENTE_MODIFICAR = 311;
	
	public static final int CLIENTE_LISTAR_OK = 312;
	public static final int CLIENTE_LISTAR_ERROR = 313;
	public static final int CLIENTE_LISTAR = 314;
	
	public static final int CLIENTE_VISTA_CREAR = 315;
	public static final int CLIENTE_VISTA_ELIMINAR = 316;
	public static final int CLIENTE_VISTA_MODIFICAR = 317;
	public static final int CLIENTE_VISTA_BUSCAR = 318;
	public static final int CLIENTE_VISTA_LISTAR = 319;
	public static final int CLIENTE_VISTA_INICIO = 320;
	
	public static final int CLIENTE_CREAR_CANCEL = 321;
	public static final int CLIENTE_ELIMINAR_CANCEL = 322;
	public static final int CLIENTE_MODIFICAR_CANCEL = 323;


		
		//EVENTOS SUBSISTEMA PRODUCTOS: 4XX
	public static final int PRODUCTOS_CREAR_OK = 400;
	public static final int PRODUCTOS_CREAR_ERROR = 401;
	public static final int PRODUCTOS_CREAR = 402;
	
	public static final int PRODUCTOS_ELIMINAR_OK = 403;
	public static final int PRODUCTOS_ELIMINAR_ERROR = 404;
	public static final int PRODUCTOS_ELIMINAR = 405;
	
	public static final int PRODUCTOS_BUSCAR_OK = 406;
	public static final int PRODUCTOS_BUSCAR_ERROR = 407;
	public static final int PRODUCTOS_BUSCAR = 408;
	
	public static final int PRODUCTOS_MODIFICAR_OK = 409;
	public static final int PRODUCTOS_MODIFICAR_ERROR = 410;
	public static final int PRODUCTOS_MODIFICAR = 411;
	
	public static final int PRODUCTOS_LISTAR_OK = 412;
	public static final int PRODUCTOS_LISTAR_ERROR = 413;
	public static final int PRODUCTOS_LISTAR = 414;
	
	public static final int PRODUCTOS_VISTA_CREAR = 415;
	public static final int PRODUCTOS_VISTA_ELIMINAR = 416;
	public static final int PRODUCTOS_VISTA_MODIFICAR = 417;
	public static final int PRODUCTOS_VISTA_BUSCAR = 418;
	public static final int PRODUCTOS_VISTA_LISTAR = 419;
	public static final int PRODUCTOS_VISTA_INICIO = 420;
	
	public static final int PRODUCTOS_CREAR_CANCEL = 421;
	public static final int PRODUCTOS_ELIMINAR_CANCEL = 422;
	public static final int PRODUCTOS_MODIFICAR_CANCEL = 423;
		
		
		//EVENTOS SUBSISTEMA EVENTOS: 5XX
	
	public static final int EVENTOS_VISTA_INICIO = 500;
	public static final int EVENTOS_VISTA_CREAR = 501;
	public static final int EVENTOS_VISTA_ELIMINAR = 502;
	public static final int EVENTOS_VISTA_BUSCAR = 503;
	public static final int EVENTOS_VISTA_LISTAR = 504;
	public static final int EVENTOS_VISTA_MODIFICAR = 505;
	
	public static final int EVENTOS_CREAR = 510;
	public static final int EVENTOS_CREAR_OK = 511;
	public static final int EVENTOS_CREAR_ERROR = 512;
	
	public static final int EVENTOS_ELIMINAR = 520;
	public static final int EVENTOS_ELIMINAR_OK = 521;
	public static final int EVENTOS_ELIMINAR_ERROR = 522;
	
	public static final int EVENTOS_BUSCAR = 530;
	public static final int EVENTOS_BUSCAR_OK = 531;
	public static final int EVENTOS_BUSCAR_ERROR = 532;
	
	public static final int EVENTOS_LISTAR = 540;
	public static final int EVENTOS_LISTAR_OK = 541;
	public static final int EVENTOS_LISTAR_ERROR = 542;
	
	public static final int EVENTOS_MODIFICAR = 550;
	public static final int EVENTOS_MODIFICAR_OK = 551;
	public static final int EVENTOS_MODIFICAR_ERROR = 552;
	
		
		//EVENTOS SUBSISTEMA ATRACCIONES: 6XX
		
		public static final int ATRACCIONES_CREAR = 600;
		public static final int ATRACCIONES_CREAR_OK = 601;
		public static final int ATRACCIONES_CREAR_ERROR = 602;
		
		public static final int ATRACCIONES_BUSCAR = 603;
		public static final int ATRACCIONES_BUSCAR_OK = 604;
		public static final int ATRACCIONES_BUSCAR_ERROR = 605;
		
		public static final int ATRACCIONES_LISTAR = 606;
		public static final int ATRACCIONES_LISTAR_OK = 607;
		public static final int ATRACCIONES_LISTAR_ERROR = 608;
		
		public static final int ATRACCIONES_ELIMINAR = 609;
		public static final int ATRACCIONES_ELIMINAR_OK = 610;
		public static final int ATRACCIONES_ELIMINAR_ERROR = 611;
		
		public static final int ATRACCIONES_MODIFICAR = 612;
		public static final int ATRACCIONES_MODIFICAR_OK = 613;
		public static final int ATRACCIONES_MODIFICAR_ERROR = 614;
		
		public static final int ATRACCIONES_VISTA_INICIO = 615;
		public static final int ATRACCIONES_VISTA_CREAR = 616;
		public static final int ATRACCIONES_VISTA_BUSCAR = 617;
		public static final int ATRACCIONES_VISTA_LISTAR = 618;
		public static final int ATRACCIONES_VISTA_ELIMINAR = 619;
		public static final int ATRACCIONES_VISTA_MODIFICAR = 620;
		

		//EVENTOS SUBSISTEMA FACTURAS: 7XX
	public static final int FACTURAS_INICIO = 700;

	public static final int FACTURAS_ANYADIR = 710;
	public static final int FACTURAS_ANYADIR_OK = 711;
	public static final int FACTURAS_ANYADIR_KO = 712;
	public static final int FACTURAS_ANYADIR_VISTA = 713;
	
	public static final int FACTURAS_BUSCAR = 720;	
	public static final int FACTURAS_BUSCAR_OK = 721;
	public static final int FACTURAS_BUSCAR_KO = 722;
	public static final int FACTURAS_BUSCAR_VISTA = 723;
	
	public static final int FACTURAS_LISTAR = 730;
	public static final int FACTURAS_LISTAR_OK = 731;
	public static final int FACTURAS_LISTAR_KO = 732;
	public static final int FACTURAS_CONSULTAR_LINEAS = 733;
	public static final int FACTURAS_LINEAS_OK_2LISTAR = 734;
	public static final int FACTURAS_LINEAS_KO = 735;
	public static final int FACTURAS_LINEAS_OK_2INI = 736;


	
	

		
	
}
