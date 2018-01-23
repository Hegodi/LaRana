;*******************************************************************************************************************
;*****													DATOS													****
;*******************************************************************************************************************




;-------------------------------------------------------------------------------------------------------------------
;-------------------------------------------------------------------------------------------------------------------
;	TIPOS
;-------------------------------------------------------------------------------------------------------------------
;-------------------------------------------------------------------------------------------------------------------

Type tipoficha
	Field vx#,vy#,vz#	; velocidad
	Field o				; Tipo de objeto
End Type
	

Type Numero
	Field m%	;Unidades de millar
	Field c%	;centenas
	Field d%	;deccenas
	Field u%	;unidades
End Type

Type Escenario
	Field suelo		;objeto suelo (siempre debe existir)
	Field suelo_t	;textura suelo (siempre debe existir)
	Field cielo		;objeto cielo (siempre debe existir)
	Field cielo_t	;textura cielo (siempre debe existir)
	Field obj1		;otros objetos
	Field obj1_t	;otras texturas
	Field obj2
	Field obj2_t
	Field obj3
	Field obj3_t
End Type
;-------------------------------------------------------------------------------------------------------------------
;-------------------------------------------------------------------------------------------------------------------
;		CONSTANTES Y VARIABLES GLOBALES
;-------------------------------------------------------------------------------------------------------------------
;-------------------------------------------------------------------------------------------------------------------


;Acentos
	
Global Acento

Global GRAV#=0		;Gravedad
Global CajaX=0		;Posicion x de la caja
Global CajaY=-3		;Posicion x de la caja
Global CajaZ=0		;Posicion x de la caja

;Luces y cámaras
Global camera
Global minicamera
Global light

; Interfaz
Global Interfaz
Global med_fuer
Global ind_fuer
Global interfaz1
Global salir_i
Global fuenteIp
Global fuenteIg
Global muestra_mini = True	; Minicamara
Dim jugadoresnum.Numero(4)	;Unidades decenas y centenas de la puntucación de cada jugador
		jugadoresnum(1) = New Numero
		jugadoresnum(2) = New Numero
		jugadoresnum(3) = New Numero
		jugadoresnum(4) = New Numero
		
;Escenario
Global decorado.Escenario = New Escenario
Global escenario_actual% = 3	; escenario actual 	0-> enrenamiento
							;					    1-> campo
							;					    2->	Casa
							;					    3-> Patio
							;					    4-> Luna (LOGRO)
						
							
Global escenariosDisp% = 1	; Escenarios disponibles +5 si la luna está

;Montaje de la rana
Global Rana
Global Caja
Global Molino
Global PuenteI
Global PuenteD
Global Fondo
Global Patas
Global Bisagra
Global puntero
Global Mano
Global Grupo_mano

Global Vel_Molino#=0; Velocidad de giro del molino


;Nerviosismo y dificultad
Global Pres_A=20  		;Presion: frecuencia de cambio de direccion
Global Pres_B=40		;Presion: velociadd de desplazamiento(inverso)
Global DivAngH# = 14.2	; Divisor del ángulo horizontal
Global DivAngV# = 4.0	; Divisor del ángulo vertival

Global NivelSel = 0
Global NivelMax = 3	; Nivel máximo alcanzado


;Fichas
Global Num_Fichas=1	; Número de fichas por tirada
Global FT					; Fichas Tiradas
Dim fichas.tipoficha(10) 	; Puntero a las fichas
Dim COL_fichas(10) 			; Identificador de colisiones de las fichas
Dim puntos_fichas(10)		; Puntos de cada ficha

;Gráficos
Const ANCHO=800	; Ancho de la ventana (px)
Const ALTO=600	; Alto de la ventana (px)

;Coeficientes de rozamiento
Const Coef_roza_madera#=0.5		;Rozamiento Madera
Const Coef_elas_madera#=0.15	;Coeficiente de elasticidad Madera
Const Coef_roza_metal#=0.6		;Rozamiento Metal
Const Coef_elas_metal#=0.2		;Coeficiente de elasticidad Metal
Const Coef_elas_fichas#=0.4     ;Coeficiente de elasticidad fichas
Const Coef_roza_fichas#=1.     ;Coeficiente de elasticidad fichas
Const Impulso_Molino# = 6.		;Impulso del molino

Global Coef_elas_metal_t#
Global Coef_elas_madera_t#

;Colisiones
Const C_ficha=11
Const C_madera=12
Const C_metal=13
Const C_molino=14
Const C_escenario=15

;Varios
Global GRAVEDAD# = 0.5		; Valor de la Gravedad por defecto
Const VCERO#=0.01  			; Velocidad que se considera cero
Const VMAX=100				
Const Tiempo_Maximo=2000 	;Tiempo máximo entre una ficha y otra (en milisegundos)
Const DistanciaX=0			; Distancia de tiro (centrado)
Const DistanciaY=5			; Alura de la posición de tiro
Const DistanciaZ=-40		; Distancia de tiro
Const FUERA=1000			; Punto que está fuera de la pantalla
Global TipoPractica = False	; Para sacar o no el ganador
Global sonido = True

;Jugadores y Puntuación
Global num_rondas=2		; Número de rondas
Global ronda				; Ronda actual
Global JUG					; Jugador actual
Global num_jug			; numero de jugadores
Dim jugadores(5)			; Punatuaciones de los jugadores
Dim jugadores_tipo(5)       ; tipo de jugador: 	0 --> persona
							;					1--> CPU
							
Const CPU = 1

;FPS
Global time1#
Global time2#
Global time0#
Global inicio
Const FPS = 30
Global mspf# = 1000./FPS

;Logros
Dim Logros_desbloqueados(9)	;Tabla de logros desbloqueados
								; 	1->Rana dorada
								;	2->fichas de fuego
								;	3->Puente de cristal
								;	4->Molino loco	
								;	5->Escenario Luna
								;	6->Fichas locas
								;	7->
								;	8->
For k = 1 To 8 
	Logros_desbloqueados(k) = False
Next
								
Logros_desbloqueados(7) = True
Logros_desbloqueados(8) = True
	
Global TipoRana   = 0	;0 --> default
						;1 --> dorada

Global TipoPuente = 0	;0 --> default
						;1 --> cristal

Global TipoFichas = 0	;0 --> default
						;1 --> locas
						;2 --> fuego  Con fichas de fuego no se puede usar la segunda cámara

Global TipoMolino = 0	;0 --> default
						;1 --> loco
							

Global Rana_tex

Global Puentes_tex

 
Global fichas_tex
Dim fichas_sprite(10)




;Menú
Global fuente_m
Global fuente_m_p
Global fondo_m
Global fondo_m_p
Global fondo_g
Global fondo_l
Global bloq_img
Global boton

;Estadísticas

Global desafios_0_jug
Global desafios_0_gan
Global desafios_1_jug
Global desafios_1_gan
Global desafios_2_jug
Global desafios_2_gan
Global desafios_3_jug
Global desafios_3_gan

Global ranas_tmp
Global ranas_max
Global molinos_tmp
Global molinos_max
Global puentes_tmp
Global puentes_max
Global puntos_tir_max
Global puntos_tir_tmp
Global puntos_par_tmp
Global puntos_par_max 



; Sonidos


Global son_mad	; sonido de la madera
Global son_met	; sonido del metal