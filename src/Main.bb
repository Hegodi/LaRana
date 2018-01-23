;*******************************************************************************************************************
;*******************************************************************************************************************
;*******************************************************************************************************************
;*****																											****
;*****																											****
;*****												LA RANA														****
;*****																											****
;*****																											****
;*****										  Diego González 2012												****
;*****																											****
;*******************************************************************************************************************
;*******************************************************************************************************************
;*******************************************************************************************************************


; Inicializamos la semilla
SeedRnd MilliSecs()

Include "datos.bb"
Include "Intro.bb"


Include "Creditos.bb"
Include "cargaguarda.bb"
Include "Menu.bb"
Include "escenarios.bb"
Include "funcionesCB.bb"



;-------------------------------------------------------------------------------------------------------------------
;-------------------------------------------------------------------------------------------------------------------
;-------------------------------------------------------------------------------------------------------------------
;		BUCLE PRINCIPAL
;-------------------------------------------------------------------------------------------------------------------
;-------------------------------------------------------------------------------------------------------------------

Function Comienza_Juego()

	Graphics3D 800,600,2,2
	;------------------------------------------------------
	;	COLISIONES
	For JUG = 0 To num_jug
		jugadores(JUG) = 0
	Next
	TraducePuntos()

	
	;Colisiones de la ficha que se tira con los objetos
	Collisions C_ficha,C_madera,2,2	;desliza en todas
	Collisions C_ficha,C_metal,2,2	;desliza en todas
	Collisions C_ficha,C_molino,2,2 ;desliza en todas
	Collisions C_ficha,C_escenario,2,1 ;se para
	
	;Colisiones de las ficha que se tira con las demás
	Collisions C_ficha,1,2,2
	Collisions C_ficha,2,2,2
	Collisions C_ficha,3,2,2
	Collisions C_ficha,4,2,2
	Collisions C_ficha,5,2,2
	Collisions C_ficha,6,2,2
	Collisions C_ficha,7,2,2
	Collisions C_ficha,8,2,2
	Collisions C_ficha,9,2,2
	Collisions C_ficha,10,2,2


	;Colisiones de las fichas con objetos

	For k=1 To 10
		Collisions k,C_madera,2,1	;se para
		Collisions k,C_metal,2,2	;se para
		Collisions k,C_molino,2,2	;Desliza
		Collisions k,C_escenario,2,1 ;desliza en todas
		
		;Colisiones de las fichas entre ellas
		For z=1 To 10
			Collisions k,z,2,2		;se para
		Next
	Next


	;-------------------------------------------------------
	; Cámaras y luces
	
	SetBuffer BackBuffer()

	;Cámara principal
	camera=CreateCamera()
	CameraViewport camera,0,0,800,600
	PositionEntity camera, 0,10,-50
	RotateEntity camera,0,0,0
	CameraFogMode camera,0
	
	;Miniatura en la parte superior
	minicamera = CreateCamera()
	CameraViewport minicamera,10,10,240,180
	PositionEntity minicamera, 0,10,-5
	RotateEntity minicamera,70,0,0
	
	;luces
	light=CreateLight(2)
	LightColor light,255,255,255
	AmbientLight 100,100,0
	PositionEntity light,CajaX,CajaY+50,CajaZ
	
	
	
	
	
	;Gravedad
	If escenario_actual = 4 Then 
		GRAVEDAD# = 0.2
	Else
		GRAVEDAD# = 0.5
	End If

	;Coeficientes de las colisiones
	If TipoFichas = 1 Then
		Coef_elas_madera_t# = 0.9
		Coef_elas_metal_t# = 0.9
	Else
		Coef_elas_madera_t# = Coef_elas_madera
		Coef_elas_metal_t# = Coef_elas_metal
	End If
	
	;-----------------------------------------------------------
	; Sonido
	
	;Carga los sonidos
	carga_sonidos

	If sonido = False Then
		SoundVolume son_mad,0.
		SoundVolume son_met,0.
	Else
		SoundVolume son_mad,0.5
		SoundVolume son_met,0.8
	End If

	;Carga la rana
	Carga_Rana()
	;Carga el escenario
	Crea_Escenario()

	;Crea la mano y el punto d emira
	Crea_Grupo_Mano()

	;Carga el interfaz
	Carga_Interfaz()
	Res% = 0
	
	

	For ronda = 1 To num_rondas
		For JUG = 1 To num_jug
			;crea las fichas
			Crea_Fichas()
			; Minicámera
			If  muestra_mini = True Then
				ShowEntity minicamera
			Else
				HideEntity minicamera
			End If
			;Numero de fichas
			FT = 0
			For kk=1 To Num_Fichas	
	       		FT=FT+1
		   		Posicion_Inicial(kk)
			   	If ((kk = 1) And (JUG = 1)) Then SiguienteRonda(ronda)
				If jugadores_tipo(JUG) = CPU Then
					ApuntaCPU(kk,NivelSel)
					Mueve_Fichas(0)
				Else
					Res = Apunta(kk)
					If (Res = -1) Then Return
		   			Mueve_Fichas(0)
				End If
				
				COL_fichas(kk)=kk
			  	EntityType fichas(kk)\o,COL_fichas(kk)
			Next
			Mueve_Fichas(1)	;Poner algo para que se relajen la última ficha
			Borra_Fichas_Arriba()
			Ver_Tirada()
			CuentaPuntos()
			Borra_Fichas_Puntos()
			TraducePuntos()	
		Next
	Next
	
	
	PuntuacionFinal()
	
	Borra_Interfaz()
	Borra_escenario()
	Borra_Rana()
	Borra_Grupo_Mano()
	Borra_sonidos()
	
	FreeEntity camera
	FreeEntity minicamera
	FreeEntity light
	
	
End Function
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;	FIN
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;




;///////////////////////////////////////////////////////////////////////////////////////////////
;///////////////////////////////////////////////////////////////////////////////////////////////
;FUNCIONES
;///////////////////////////////////////////////////////////////////////////////////////////////
;///////////////////////////////////////////////////////////////////////////////////////////////




;Crea los objetos ficha y lo coloca fuera de escena


; Permite apuntar para lanzar el objeto bola
Function Apunta(Indice)

	If TipoFichas = 2 Then muestra_mini = False
	
	
	;Salir
	

    ;Ponemos la animación de la mano en el frame 0
	AnimateMD2 Mano,3,0,1,1
	ShowEntity puntero
    ;Asociamos la ficha la mano y el punto de ira con el pivote Grupo_mano
    x#=EntityX(fichas(Indice)\o)
    y#=EntityY(fichas(Indice)\o)+5
    z#=EntityZ(fichas(Indice)\o)-10

    PositionEntity Grupo_mano,x,y,z
    EntityParent Mano,Grupo_mano
    EntityParent fichas(Indice)\o,Grupo_mano
    EntityParent puntero,Grupo_mano

    Cont=0
    PulsoX#=0
    PulsoY#=0
    PulsoZ#=0
    Fuerza#=3

	Inc# = 0.1

    Ya=0
	
	If TipoMolino = 1 Then Vel_Molino = Rnd(-15,15)

	i_ff% = 0
	Repeat 
	
	
		i_ff = i_ff +1 
		;fichas de fuego
		If TipoFichas = 2 Then
			If i_ff Mod 5 = 0 
				ii_ff = ii_ff +1
				If ii_ff > 4 Then ii_ff = 0
				For k = 1 To Num_fichas
					EntityTexture fichas_sprite(k),fichas_tex,ii_ff
				Next
			End If
		End If
	
		If TipoMolino = 1 Then 
			TurnEntity Molino,Vel_Molino#,0,0
		End If
	
		; Pulsa M para quitar la minicámara
		If KeyHit(50) Then
			If muestra_mini = True Then
				muestra_mini = False
				HideEntity minicamera
			Else
				If fichas_fuego = False Then
					muestra_mini = True
					ShowEntity minicamera
				End If
					
			End If
		
		End If
		
		ControlaFPS()
		;Interfaz
		Dibuja_Interfaz(Fuerza)
		If MouseDown(1)	
			If (MouseX() > 710) And (MouseX() < 775) And (MouseX() > 10) And (MouseY() < 30) Then Return -1	
			If Fuerza>6 Then Inc=-0.1
			If Fuerza<3 Then Inc = 0.1 
			Fuerza#=Fuerza#+Inc 
			Ya=1
		EndIf
		
	
		;NERVIOSISMO
		If Rand(1,Pres_A)=1 Then px#=Rnd(-1,1)
		If Rand(1,Pres_A)=1 Then py#=Rnd(-1,1)
		If Rand(1,Pres_A)=1 Then pz#=Rnd(-1,1)

		PulsoX=PulsoX+px/Pres_B + corrX#
		PulsoY=PulsoY+py/Pres_B + corrY#
		PulsoZ=PulsoZ+pz/Pres_B + corrZ#
		


		dx#=x+PulsoX 
		dy#=y+PulsoY 
		dz#=z+PulsoZ 

		
		If dx < -1.5 Then corrX = 1./Pres_B
		If dx >  1.5 Then corrX =-1./Pres_B
		If dy <  6.5 Then corrY = 1./Pres_B
		If dy >  8.5 Then corrY =-1./Pres_B
		If dz < -51 Then corrZ = 1./Pres_B
		If dz > -49 Then corrZ =-1./Pres_B
		
		If (dx>-0.5) And (dx<0.5) Then corrX = 0.
		If (dy>7) And (dy<8) Then corrY = 0.
		If (dz>-50.5) And (dz<-49.5) Then corrZ = 0.
		
		;Control de la dirección
		If (MouseY()> 50) And (MouseY() <550) Then
			AngV#=((MouseY())/DivAngV#-35)	;¿Pulso aquí también?
		EndIf
		
		If (MouseX()> 100) And (MouseX() <700) Then
			AngH#=((MouseX()-400.)/DivAngH#) ;¿Pulso aquí también?
		EndIf	
 
		;Control de la posición
		If KeyDown(203) And (x >-3 ) Then x = x - 0.1 ; Izquierda
    	If KeyDown(205) And (x < 3 ) Then x = x + 0.1 ;Derecha

	
	    PositionEntity Grupo_mano,dx#,dy#,dz#
	    RotateEntity puntero,-AngV+20,-1.8*AngH,0

	    Cont=Cont+1
	    Flip
	    UpdateWorld
	    RenderWorld

	Until (Not MouseDown(1)) And Ya=1

    fichas(Indice)\vx=Sin(AngH)*Fuerza
    fichas(Indice)\vy=Sin(AngV)*Fuerza*Cos(AngH)
    fichas(Indice)\vz=Cos(AngV)*Fuerza

	HideEntity puntero
    EntityParent Mano,0
    EntityParent fichas(Indice)\o,0
    EntityParent puntero,0

	Return 0

End Function

; Función para paruntar cuando juega el ordenador
Function ApuntaCPU(Indice,Dificultad)
	AngH# = 0
	AngV# = 0
	Fuerza# = 0
	Select Dificultad
		Case 0
			AngV = Rnd(30,60)
			Fuerza = Rnd(4.40,4.55)
			AngH = Rnd(-3,3)
		Case 1
			AngV = Rnd(30,60)
			Fuerza = Rnd(4.40,4.55)
			AngH = Rnd(-2.5,2.5)			
		Case 2
			AngV = Rnd(40,55)
			Fuerza = Rnd(4.40,4.55)
			AngH = Rnd(-2.0,2.0)
		Case 3
			AngV = Rnd(40,55)
			Fuerza = Rnd(4.45,4.53)
			AngH = Rnd(-1.2,1.2)
			
	End Select

	fichas(Indice)\vx=Sin(AngH)*Fuerza
    fichas(Indice)\vy=Sin(AngV)*Fuerza*Cos(AngH)
    fichas(Indice)\vz=Cos(AngV)*Fuerza
    HideEntity puntero

End Function

;-----------------------------------------------------------------------------------------------


Function TraducePuntos()
	For k = 1 To num_jug
		jugadoresnum(k)\m = jugadores(k)/1000
		jugadoresnum(k)\c = jugadores(k)/100 - 10*jugadoresnum(k)\m
		jugadoresnum(k)\d = jugadores(k)/10 - jugadoresnum(k)\c*10 - jugadoresnum(k)\m*100
		jugadoresnum(k)\u = jugadores(k) - jugadoresnum(k)\d*10 - jugadoresnum(k)\c*100 - jugadoresnum(k)\m*1000
	Next

End Function

;Dibuja Interfaz
Function Dibuja_Interfaz(F#)
	
	DrawImage Interfaz,0,0
	DrawImage ind_fuer,100,235-(75*(F-6)) ;520 ;300
	
	
	
	
	;Jugador
	DrawImage fuenteIp,730,535,26+JUG ;4
	;--------------------------------------

	;--------------------------------------
	DrawImage fuenteIg,575,460,26+jugadoresnum(JUG)\m ;millares
	DrawImage fuenteIg,615,460,26+jugadoresnum(JUG)\c ;centenas
	DrawImage fuenteIg,655,460,26+jugadoresnum(JUG)\d ;decenas
	DrawImage fuenteIg,695,460,26+jugadoresnum(JUG)\u ;inidades	;695
	;--------------------------------------

	;Ronda
	;--------------------------------------
 	d% = ronda/10 	;decenas
	u% = ronda-d*10	;unidades
	;--------------------------------------
	DrawImage fuenteIp,715,385,26+d ;1
	DrawImage fuenteIp,733,385,26+u ;0
	;--------------------------------------

	;Fichas
	;--------------------------------------
	d% = FT/10 	;decenas
	u% = FT-d*10	;unidades
	DrawImage fuenteIp,690,420,26+d
	DrawImage fuenteIp,708,420,26+u

	;Puntuación otros jugadores
	For k = 1 To num_jug
		DrawImage fuenteIp,701,120+k*50,26+jugadoresnum(k)\m ;m
		DrawImage fuenteIp,713,120+k*50,26+jugadoresnum(k)\c ;0
		DrawImage fuenteIp,725,120+k*50,26+jugadoresnum(k)\d ;0
		DrawImage fuenteIp,737,120+k*50,26+jugadoresnum(k)\u ;0
	Next
	
	;Salir
	Color 112,64,3
	Rect 710,10,65,20
	Color 255,255,255
	Text 720,15,"SALIR"

End Function



;-----------------------------------------------------------------------------------------------
;Coloca la camara, la caja y la ficha indicada en la posicion inicial
Function Posicion_Inicial(Indice)


 PosX= CajaX-DistanciaX
 PosY= CajaY+DistanciaY
 PosZ=CajaZ+DistanciaZ

 PositionEntity Mano,PosX+1.5,PosY+2,PosZ-0.4

 PositionEntity fichas(Indice)\o,PosX,PosY,PosZ
 PositionEntity puntero,PosX,PosY+1,PosZ+1

 
 CameraZoom camera, 1.8
 PositionEntity camera, CajaX-DistanciaX,CajaY+DistanciaY+7,CajaZ+DistanciaZ-16
 RotateEntity Mano,-10,-20,0
 RotateEntity camera,12,0,0
 RotateEntity Bisagra,0,0,0
 

 Flip
 UpdateWorld
 RenderWorld

End Function


;-----------------------------------------------------------------------------------------------

;Movimiento de camara para ver la tirada
Function Ver_Tirada()
  
  HideEntity minicamera
  FIN%=DistanciaY
  Cy#=EntityY(camera)
  Cz#=EntityZ(camera)
  	If TipoFichas = 2 Then
		For k = 1 To Num_Fichas
			If puntos_fichas(k) = -2 MoveEntity fichas_sprite(k),0,1,3
		Next
	End If
  Repeat

	ControlaFPS()
    Cz=Cz+0.9
    Cy=Cy+0.1
    PositionEntity  camera,0,Cy,Cz
    TurnEntity camera,1.25,0,0

    TurnEntity Bisagra,1.1,0,0

	Flip
	UpdateWorld
	RenderWorld
  
  Until EntityZ(camera)>CajaZ
  Delay 500

End Function



;MUEVE FICHA (Calcula las colisiones si las hubiera)
;----------------------------------------------------------------
;Se le pasa las velocidades iniciales y el objeto a mover
Function Mueve_Fichas(render)
	GRAV=GRAVEDAD
   	i_ff% = 0

	
	If TipoFichas = 2 Then muestra_mini = False
	
	If  muestra_mini = True Then
		ShowEntity minicamera
	Else
		HideEntity minicamera
	End If
	
	;Molino loco
	If TipoMolino = 1 Then Vel_Molino = 10;Rnd(2,10)
	
	HideEntity fichas(FT)\o
	If render = 0 Then 
		
		AnimateMD2 Mano,3,0.95,0,25
	
		Repeat
		
			i_ff = i_ff +1 
			;fichas de fuego
			If TipoFichas = 2 Then
				If i_ff Mod 5 = 0 
					ii_ff = ii_ff +1
					If ii_ff > 4 Then ii_ff = 0
					For k = 1 To Num_fichas
						EntityTexture fichas_sprite(k),fichas_tex,ii_ff
					Next
				End If
			End If

			ControlaFPS()
			UpdateWorld
			RenderWorld
			Flip
		Until MD2AnimTime(Mano) > 15 
		
	End If
	
	
	Con=0
	
	PosXIni# = 0
	PosYIni# = 0
	PosZIni# = 0
	
	PrimeraCol = True
	
	contador = 0
	
	tt0# =  MilliSecs()
	ShowEntity fichas(FT)\o
	
	Repeat
		contador = contador +1
		i_ff = i_ff +1 
		;fichas de fuego
		If TipoFichas = 2 Then
			If i_ff Mod 5 = 0 
				ii_ff = ii_ff +1
				If ii_ff > 4 Then ii_ff = 0
				For k = 1 To Num_fichas
					EntityTexture fichas_sprite(k),fichas_tex,ii_ff
				Next
			End If
		End If

	
		;Saltar tirada si es la CPU
		If jugadores_tipo(JUG) = CPU Then 
			If MouseDown(1) Then
			
			End If 
		End If
	
		If MD2AnimTime(Mano) > 24 Then AnimateMD2 Mano,0	; Si no da error RenderWorld
		ControlaFPS()
		
		; Módulo de la velocidad
		ModVel# = Abs(PosXIni-EntityX(fichas(FT)\o)) + Abs(PosYIni-EntityY(fichas(FT)\o)) + Abs(PosZIni-EntityZ(fichas(FT)\o))
		

		If ModVel < 0.0005 Then
			Exit
		End If

	
		PosXIni# = EntityX(fichas(FT)\o)
		PosYIni# = EntityY(fichas(FT)\o)
		PosZIni# = EntityZ(fichas(FT)\o)


		;Comprobamos todas las fichas
		For k=1 To FT
				  	
			;Colisiones con las demas fichas
			For z=1 To FT
		   		If EntityCollided(fichas(k)\o,COL_fichas(z))
					CalculaColisionEntreFichas(k,z)
					If (k =FT) And (ModVel# > 0.8) Then 
						PlaySound son_met
						PrimeraCol = False
					End If
				EndIf
			Next
			
			; Colisiones con la caja
			If EntityCollided(fichas(k)\o,C_madera)   
	    		CalculaColisionPared(k,Coef_roza_madera,Coef_elas_madera_t)
				If (k =FT) And (ModVel# > 0.8) Then 
						PlaySound son_mad
						PrimeraCol = False
				End If


			End If
			
			
			;Colisiones con los objetos de metal
			If EntityCollided(fichas(k)\o,C_metal)		
				CalculaColisionPared(k,Coef_roza_metal,Coef_elas_metal_t)
				If (k =FT) And (ModVel# > 0.8) Then 
						PlaySound son_met
						PrimeraCol = False
				End If


			EndIf
			
			;Colisiones con el molino
			If EntityCollided(fichas(k)\o,C_molino)   
				VV# = CalculaColisionPared(k,Coef_roza_metal,Coef_elas_metal_t)
				VV = VV/Coef_elas_metal
				If Abs(VV) < GRAV+0.2 Then 
					Vel_Molino#=0
				Else
					Vel_Molino#=-8*VV
				EndIf
				If (k =FT) And (ModVel# > 0.8) Then 
						PlaySound son_met
						PrimeraCol = False
				End If

				
			End If	
			
			;IMPORTANTE: Tiene que ir auquí, después de calcular todas las colisiones
			; Movimiento de las fichas
			fichas(k)\vy=fichas(k)\vy-GRAV	
			MoveEntity fichas(k)\o,fichas(k)\vx,fichas(k)\vy,fichas(k)\vz
			
        Next

		TurnEntity Molino,Vel_Molino#,0,0
        
		If Molino_Loco = False Then Vel_Molino#=0.5*Vel_Molino
		  

		Flip
		UpdateWorld
		If render = 0 Then
			RenderWorld
		EndIf	
	Until (MilliSecs()-tt0 >Tiempo_Maximo)


		
	Vel_Molino=0
	GRAV=0
	
	For k = 1 To FT
		fichas(k)\vx = 0
		fichas(k)\vy = 0
		fichas(k)\vz = 0
	Next
	
	AnimateMD2 Mano,0
End Function	


;Recuento de los puntos
Function CuentaPuntos()
	
	FlushKeys()
	puntostirada% = 0
	fon =LoadImage ("Imagenes/fondo.jpg")	
	ScaleImage fon,0.8,0.8
	ranas_tmp = 0
	puentes_tmp = 0
	molinos_tmp = 0
	puntos_tir_tmp = 0
	
	
	For k = 1 To FT
		If puntos_fichas(k) = -2 Then
			x# = EntityX(fichas(k)\o)
			y# = EntityZ(fichas(k)\o)
			
			If (x>-8.4) And (x<-2.9) And (y< 9.2) And (y> 3.55)	Then puntos_fichas(k) = 5;Izq Sup  5p
			If (x>-2.9) And (x< 2.9) And (y< 9.2) And (y> 3.55) Then puntos_fichas(k) = 5;Cen Sup  5p
			If (x> 2.9) And (x< 8.4) And (y< 9.2) And (y> 3.55) Then puntos_fichas(k) = 5;Der Sup  5p
			
			If (x>-8.4) And (x<-2.9) And (y< 3.55) And (y>-2.6) Then puntos_fichas(k) = 5;Izq Cen  5p
			If (x>-2.9) And (x< 2.9) And (y< 3.55) And (y>-2.6) Then 
				puntos_fichas(k) = 50;Cen Cen  50p
				If JUG = 1 Then ranas_tmp = ranas_tmp + 1
			End If
			If (x> 2.9) And (x< 8.4) And (y< 3.55) And (y>-2.6) Then puntos_fichas(k) = 5;Der Cen  5p
			
			If (x>-8.4) And (x<-2.9) And (y<-2.6) And (y>-9.01) Then
				puntos_fichas(k) = 10;Izq Inf  10p
				If JUG = 1 Then puentes_tmp = puentes_tmp + 1
			End If
			If (x>-2.9) And (x< 2.9) And (y<-2.8) And (y>-9.01)	Then
				puntos_fichas(k) = 25;Cen Inf  25p
				If JUG = 1 Then molinos_tmp = molinos_tmp + 1
			End If
			If (x> 2.9) And (x< 8.4) And (y<-2.6) And (y>-9.01)	Then
				puntos_fichas(k) = 10;Der Inf  10p
				If JUG = 1 Then puentes_tmp = puentes_tmp + 1
			End If

		Else
			puntos_fichas(k) = 0
		End If
		
		puntostirada = puntostirada + puntos_fichas(k)
		DrawImage fon,100,50
		For kk = 1 To k
			
			;--------------------------------------
			DrawImage fuenteIg,130,80,17 ;r
			DrawImage fuenteIg,175,80,14 ;o
			DrawImage fuenteIg,220,80,13 ;n
			DrawImage fuenteIg,265,80,3 ;d
			DrawImage fuenteIg,310,80,0 ;a
			;---espacio en blanco---
			DrawImage fuenteIg,400,80,26+ronda ;0
			;--------------------------------------
	
			;--------------------------------------
			DrawImage fuenteIp,300,150,9 ;j
			DrawImage fuenteIp,330,150,20 ;u
			DrawImage fuenteIp,360,150,6 ;g
			DrawImage fuenteIp,390,150,0 ;a
			DrawImage fuenteIp,420,150,3 ;d
			DrawImage fuenteIp,450,150,14 ;o
			DrawImage fuenteIp,480,150,17 ;r
			;---espacio en blanco---
			DrawImage fuenteIp,540,150,26+JUG ;0
			;--------------------------------------
				
			d% = puntos_fichas(kk)/10
			u% = puntos_fichas(kk)-d*10
			If kk < 6 Then
				DrawImage fuenteIg,15+kk*120,240,26+d
				DrawImage fuenteIg,55+kk*120,240,26+u
			Else
				DrawImage fuenteIg,15+(kk-5)*120,350,26+d
				DrawImage fuenteIg,55+(kk-5)*120,350,26+u
			End If 
		Next
		
		
		If k = Num_Fichas Then 
			Color 12,149,9
			Rect 315,430,170,70
			
			c% = puntostirada/100
			d% = puntostirada/10-c*10
			u% = puntostirada-d*10-c*100
			
			;--------------------------------------
			DrawImage fuenteIg,325,440,26+c ;c
			DrawImage fuenteIg,375,440,26+d ;d
			DrawImage fuenteIg,425,440,26+u ;u
			;--------------------------------------
			

		End If
	If KeyHit(1) Then Exit
	Flip
	RenderWorld
	Delay 300
	Next

	; Comprobamos lo logros
	If JUG = 1 Then	
		puntos_tir_tmp = puntostirada 
	Else
		puntos_tir_tmp = 0
	End If
	CompruebaLogrosTir()

	Delay 2000
	jugadores(JUG) = jugadores(JUG) + puntostirada 
	FlushKeys()
	
	FreeImage fon

End Function


Function SiguienteRonda(nr%)

	;--------------------------------------
	DrawImage fuenteIg,225,200,17 ;r
	DrawImage fuenteIg,275,200,14 ;o
	DrawImage fuenteIg,325,200,13 ;n
	DrawImage fuenteIg,375,200,3 ;d
	DrawImage fuenteIg,425,200,0 ;a
	;---espacio en blanco---
	DrawImage fuenteIg,525,200,26+nr 
	;--------------------------------------

	Flip
	
	
	Delay 500

End Function


Function PuntuacionFinal()

	Cls
	FlushKeys()
	fon =LoadImage ("Imagenes/fondo.jpg")	
	ScaleImage fon,0.8,0.8
		
		DrawImage fon,80,50
		For k = 1 To Num_Jug
			;--------------------------------------
			DrawImage fuenteIp,150,100+75*k,9 ;j
			DrawImage fuenteIp,170,100+75*k,20 ;u
			DrawImage fuenteIp,190,100+75*k,6 ;g
			DrawImage fuenteIp,210,100+75*k,0 ;a
			DrawImage fuenteIp,230,100+75*k,3 ;d
			DrawImage fuenteIp,250,100+75*k,14 ;o
			DrawImage fuenteIp,270,100+75*k,17 ;r
			;---espacio en blanco---
			DrawImage fuenteIp,310,100+75*k,26+k ;0
			;--------------------------------------
			m% = jugadores(k)/1000
			c% = jugadores(k)/100 -m*10
			d% = jugadores(k)/10-c*10 -m*100
			u% = jugadores(k)-d*10-c*100 - m*1000
			
			;--------------------------------------
			DrawImage fuenteIp,540,100+75*k,26+m ;0
			DrawImage fuenteIp,560,100+75*k,26+c ;0
			DrawImage fuenteIp,580,100+75*k,26+d ;0
			DrawImage fuenteIp,600,100+75*k,26+u ;0
			;--------------------------------------
			Color 255,255,255
			Rect 340,110+75*k,190,5		
							
		Next
		
	Color 255,255,255
	Text 250,550,"PULSA UNA TECLA PARA CONTINUAR."	
	Flip
	RenderWorld


	WaitKey()
	Delay(100)
	FlushKeys()

	Cls
	
	If TipoPractica = False	 Then
	
	
	  DrawImage fon,100,50
	  puntos_par_tmp = jugadores(1) 
	  ganador = -1
	
	  If jugadores(1) > jugadores(2) Then
		 ganador = 1
	  Else If jugadores(2) > jugadores(1) Then	
		 ganador =  2
	  End If
	
	  If Num_Jug = 3 Then
		If (jugadores(3) >jugadores(ganador)) And (jugadores(3) > 0) Then
			ganador = 3
		End If
	  Else If Num_Jug = 4
		If (jugadores(4) >jugadores(ganador)) And (jugadores(4) > 0) Then
			ganador = 4
		End If
	  End If
	 
	  If ganador = -1 Then
		;--------------------------------------
		DrawImage fuenteIg,250,275,4 ;e
		DrawImage fuenteIg,300,275,12 ;m
		DrawImage fuenteIg,350,275,15 ;p
		DrawImage fuenteIg,400,275,0 ;a
		DrawImage fuenteIg,450,275,19 ;t
		DrawImage fuenteIg,500,275,4 ;e
		;--------------------------------------
	  Else
		;--------------------------------------
		DrawImage fuenteIp,190,200,4 ;e
		DrawImage fuenteIp,210,200,13 ;n
		DrawImage fuenteIp,230,200,7 ;h
		DrawImage fuenteIp,250,200,14 ;o
		DrawImage fuenteIp,270,200,17 ;r
		DrawImage fuenteIp,290,200,0 ;a
		DrawImage fuenteIp,310,200,1 ;b
		DrawImage fuenteIp,330,200,20 ;u
		DrawImage fuenteIp,350,200,4 ;e
		DrawImage fuenteIp,370,200,13 ;n
		DrawImage fuenteIp,390,200,0 ;a
		;---espacio en blanco---
		DrawImage fuenteIp,430,200,9 ;j
		DrawImage fuenteIp,450,200,20 ;u
		DrawImage fuenteIp,470,200,6 ;g
		DrawImage fuenteIp,490,200,0 ;a
		DrawImage fuenteIp,510,200,3 ;d
		DrawImage fuenteIp,530,200,14 ;o
		DrawImage fuenteIp,550,200,17 ;r
		;---espacio en blanco---
		DrawImage fuenteIp,590,200,26+ganador ;0
		;--------------------------------------
		;--------------------------------------
		DrawImage fuenteIg,175,300,7 ;h
		DrawImage fuenteIg,220,300,0 ;a
		DrawImage fuenteIg,265,300,18 ;s
		;---espacio en blanco---
		DrawImage fuenteIg,355,300,6 ;g
		DrawImage fuenteIg,400,300,0 ;a
		DrawImage fuenteIg,445,300,13 ;n
		DrawImage fuenteIg,490,300,0 ;a
		DrawImage fuenteIg,535,300,3 ;d
		DrawImage fuenteIg,580,300,14 ;o
		;--------------------------------------
		
	  End If
	  Color 255,255,255
	  Text 250,550,"PULSA UNA TECLA PARA CONTINUAR."
	  Flip
	  RenderWorld
	
	  ; Si es un desafío lo sumamos a las estadísticas
	  WaitKey()

	End If
	TipoPractica = False
	FlushKeys()
	CompruebaLogrosPar(ganador)
	

	FlushKeys()
	FreeImage (fon)


End Function


;Comprueba los logros después de cada tirada
Function CompruebaLogrosTir()
	
	; Si no estamos en desafío salimos
	If NivelSel < 0 Then 
		Return
	End If
	
	; Rana dorada
	If ranas_tmp > ranas_max Then
		ranas_max = ranas_tmp
		If (ranas_max > 5) And (Logros_desbloqueados(1) = False) Then	;Logro desbloqueado
			Logros_desbloqueados(1) = True
			LogroNuevo(1)
		End If
		Guarda_log()
	End If
	
	; Puentes de cristal
	If puentes_tmp > puentes_max Then
		puentes_max = puentes_tmp
		If (puentes_max > 5) And (Logros_desbloqueados(3) = False) Then	;Logro desbloqueado
			Logros_desbloqueados(3) = True
			LogroNuevo(3)
		End If
		Guarda_log()
	End If

	; Molinillo loco
	If molinos_tmp > molinos_max Then
		molinos_max = molinos_tmp
		If (molinos_max > 5) And (Logros_desbloqueados(4) = False) Then	;Logro desbloqueado
			Logros_desbloqueados(4) = True
			LogroNuevo(4)
		End If
		Guarda_log()
	End If

	; Puntuación de la tirada
	If puntos_tir_tmp > puntos_tir_max Then
		puntos_tir_max = puntos_tir_tmp
		Guarda_log()
	End If
End Function


;Comprueba los logros después de la partida completa
Function CompruebaLogrosPar(ganador%) 
	
	; Si no estamos en desafío salimos
	If NivelSel < 0 Then 
		Return
	End If
	
	
	Select NivelSel
		Case 0
			desafios_0_jug = desafios_0_jug + 1
			If ganador = 1 Then
				desafios_0_gan = desafios_0_gan +1 ; Si ha ganado el jugador 0 (yo)
			End If
		Case 1
			desafios_1_jug = desafios_1_jug + 1
			If ganador = 1 Then 
				desafios_1_gan = desafios_1_gan +1 ; Si ha ganado el jugador 0 (yo)
			End If
		Case 2
			desafios_2_jug = desafios_2_jug + 1
			If ganador = 1 Then
				desafios_2_gan = desafios_2_gan +1 ; Si ha ganado el jugador 0 (yo)
			End If
		Case 3
			desafios_3_jug = desafios_3_jug + 1
			If ganador = 1 Then
				desafios_3_gan = desafios_3_gan +1 ; Si ha ganado el jugador 0 (yo)
			End If
	End Select
	
	If (desafios_0_jug + desafios_1_jug +desafios_2_jug +desafios_3_jug > 9) Then	
		If Logros_desbloqueados(2) = False Then
			Logros_desbloqueados(2) = True
			LogroNuevo(2)
		End If
	End If
	
	If (desafios_1_gan > 4) And (Logros_desbloqueados(6) = False) Then
		Logros_desbloqueados(6) = True
		LogroNuevo(6)
	End If
	If (desafios_2_gan > 4) And (Logros_desbloqueados(7) = False) Then
		Logros_desbloqueados(7) = True
		LogroNuevo(7)
	End If
	If (desafios_3_gan > 4) And (Logros_desbloqueados(8) = False) Then
		Logros_desbloqueados(8) = True
		LogroNuevo(8)
	End If

	
	If puntos_par_tmp > puntos_par_max Then
		puntos_par_max = puntos_par_tmp
		If (puntos_par_tmp > 1500) And (Logros_desbloqueados(5) = False) Then
			Logros_desbloqueados(5) = True
			LogroNuevo(5)
		End If
 
	End If
	
	Guarda_log()
	
	
	; Puntuación de la partida

End Function

; Informa del logro desbloqueado.
Function LogroNuevo(ind%)
	fon =LoadImage ("Imagenes/fondo.jpg")
	logros_img = LoadAnimImage ("Imagenes/logros.jpg",150,150,0,8)	
	ScaleImage fon,0.8,0.8
	
	DrawImage fon,80,50
	
	;--------------------------------------
	DrawImage fuenteIp,220,120,11 ;l
	DrawImage fuenteIp,240,120,14 ;o
	DrawImage fuenteIp,260,120,6 ;g
	DrawImage fuenteIp,280,120,17 ;r
	DrawImage fuenteIp,300,120,14 ;o
	;---espacio en blanco---
	DrawImage fuenteIp,340,120,3 ;d
	DrawImage fuenteIp,360,120,4 ;e
	DrawImage fuenteIp,380,120,18 ;s
	DrawImage fuenteIp,400,120,1 ;b
	DrawImage fuenteIp,420,120,11 ;l
	DrawImage fuenteIp,440,120,14 ;o
	DrawImage fuenteIp,460,120,16 ;q
	DrawImage fuenteIp,480,120,20 ;u
	DrawImage fuenteIp,500,120,4 ;e
	DrawImage fuenteIp,520,120,0 ;a
	DrawImage fuenteIp,540,120,3 ;d
	DrawImage fuenteIp,560,120,14 ;o
	;--------------------------------------
	Color 0,0,0
	Rect 325,220,150,150
	DrawImage logros_img,325,220,ind-1
	


	Select ind
		Case 1	; Rana Dorada
			;--------------------------------------
			DrawImage fuenteIp,290,450,17 ;r
			DrawImage fuenteIp,310,450,0 ;a
			DrawImage fuenteIp,330,450,13 ;n
			DrawImage fuenteIp,350,450,0 ;a
			;---espacio en blanco---
			DrawImage fuenteIp,390,450,3 ;d
			DrawImage fuenteIp,410,450,14 ;o
			DrawImage fuenteIp,430,450,17 ;r
			DrawImage fuenteIp,450,450,0 ;a
			DrawImage fuenteIp,470,450,3 ;d
			DrawImage fuenteIp,490,450,0 ;a
			;--------------------------------------
		
		Case 2	; Fichas de fuego
			;--------------------------------------
			DrawImage fuenteIp,250,450,5 ;f
			DrawImage fuenteIp,270,450,8 ;i
			DrawImage fuenteIp,290,450,2 ;c
			DrawImage fuenteIp,310,450,7 ;h
			DrawImage fuenteIp,330,450,0 ;a
			DrawImage fuenteIp,350,450,18 ;s
			;---espacio en blanco---
			DrawImage fuenteIp,390,450,3 ;d
			DrawImage fuenteIp,410,450,4 ;e
			;---espacio en blanco---
			DrawImage fuenteIp,450,450,5 ;f
			DrawImage fuenteIp,470,450,20 ;u
			DrawImage fuenteIp,490,450,4 ;e
			DrawImage fuenteIp,510,450,6 ;g
			DrawImage fuenteIp,530,450,14 ;o
			;--------------------------------------
		
		Case 3  ; Puentes de cristal
			;--------------------------------------
			DrawImage fuenteIp,220,450,15 ;P
			DrawImage fuenteIp,240,450,20 ;u
			DrawImage fuenteIp,260,450,4 ;e
			DrawImage fuenteIp,280,450,13 ;n
			DrawImage fuenteIp,300,450,19 ;t
			DrawImage fuenteIp,320,450,4 ;e
			DrawImage fuenteIp,340,450,18 ;s
			;---espacio en blanco---
			DrawImage fuenteIp,380,450,3 ;d
			DrawImage fuenteIp,400,450,4 ;e
			;---espacio en blanco---
			DrawImage fuenteIp,440,450,2 ;c
			DrawImage fuenteIp,460,450,17 ;r
			DrawImage fuenteIp,480,450,8 ;i
			DrawImage fuenteIp,500,450,18 ;s
			DrawImage fuenteIp,520,450,19 ;t
			DrawImage fuenteIp,540,450,0 ;a
			DrawImage fuenteIp,560,450,11 ;l
			;--------------------------------------
		Case 4	; Molino loco
			;--------------------------------------
			DrawImage fuenteIp,290,450,12 ;m
			DrawImage fuenteIp,310,450,14 ;o
			DrawImage fuenteIp,330,450,11 ;l
			DrawImage fuenteIp,350,450,8 ;i
			DrawImage fuenteIp,370,450,13 ;n
			DrawImage fuenteIp,390,450,14 ;o
			;---espacio en blanco---
			DrawImage fuenteIp,430,450,11 ;l
			DrawImage fuenteIp,450,450,14 ;o
			DrawImage fuenteIp,470,450,2 ;c
			DrawImage fuenteIp,490,450,14 ;o
			;--------------------------------------
		
		Case 5  ; Escenario lunar
			;--------------------------------------
			DrawImage fuenteIp,250,450,4 ;e
			DrawImage fuenteIp,270,450,18 ;s
			DrawImage fuenteIp,290,450,2 ;c
			DrawImage fuenteIp,310,450,4 ;e
			DrawImage fuenteIp,330,450,13 ;n
			DrawImage fuenteIp,350,450,0 ;a
			DrawImage fuenteIp,370,450,17 ;r
			DrawImage fuenteIp,390,450,8 ;i
			DrawImage fuenteIp,410,450,14 ;o
			;---espacio en blanco---
			DrawImage fuenteIp,450,450,11 ;l
			DrawImage fuenteIp,470,450,20 ;u
			DrawImage fuenteIp,490,450,13 ;n
			DrawImage fuenteIp,510,450,0 ;a
			DrawImage fuenteIp,530,450,17 ;r
			;--------------------------------------
		
		Case 6  ; Fichas locas
			;--------------------------------------
			DrawImage fuenteIp,280,450,5 ;f
			DrawImage fuenteIp,300,450,8 ;i
			DrawImage fuenteIp,320,450,2 ;c
			DrawImage fuenteIp,340,450,7 ;h
			DrawImage fuenteIp,360,450,0 ;a
			DrawImage fuenteIp,380,450,18 ;s
			;---espacio en blanco---
			DrawImage fuenteIp,420,450,11 ;l
			DrawImage fuenteIp,440,450,14 ;o
			DrawImage fuenteIp,460,450,2 ;c
			DrawImage fuenteIp,480,450,0 ;a
			DrawImage fuenteIp,500,450,18 ;s
			;--------------------------------------
		
		Case 7  ; Sin rana
			;--------------------------------------
			DrawImage fuenteIp,320,450,18 ;S
			DrawImage fuenteIp,340,450,8 ;i
			DrawImage fuenteIp,360,450,13 ;n
			;---espacio en blanco---
			DrawImage fuenteIp,400,450,17 ;r
			DrawImage fuenteIp,420,450,0 ;a
			DrawImage fuenteIp,440,450,13 ;n
			DrawImage fuenteIp,460,450,0 ;a
			;--------------------------------------
		
		Case 8  ; Sin molino
			;--------------------------------------
			DrawImage fuenteIp,300,450,18 ;S
			DrawImage fuenteIp,320,450,8 ;i
			DrawImage fuenteIp,340,450,13 ;n
			;---espacio en blanco---
			DrawImage fuenteIp,380,450,12 ;m
			DrawImage fuenteIp,400,450,14 ;o
			DrawImage fuenteIp,420,450,11 ;l
			DrawImage fuenteIp,440,450,8 ;i
			DrawImage fuenteIp,460,450,13 ;n
			DrawImage fuenteIp,480,450,14 ;o
			;--------------------------------------

	End Select
Flip
RenderWorld
Delay 2000
	
	FreeImage logros_i
	FreeImage fon
	
End Function

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;				COLISIONES


;Calcula una colisión. Argumentos: indice de la ficha, coeficiente de rozamiento, coeficiente elástico.
Function CalculaColisionPared#(ind%,CR#,CE#)

	Nx#=CollisionNX(fichas(ind)\o,1)
	Ny#=CollisionNY(fichas(ind)\o,1)
	Nz#=CollisionNZ(fichas(ind)\o,1)	
	vN#=-(fichas(ind)\vx*Nx+fichas(ind)\vy*Ny+fichas(ind)\vz*Nz)*CE

	If Nx <>0 
		P1mod#=Sqr(((-Ny-Nz)/Nx)*((-Ny-Nz)/Nx)+2.)
		P1x#=((-Ny-Nz)/Nx)/P1mod
		P1y#=1./P1mod
		P1z#=1./P1mod
	Else If Ny <> 0
		P1mod#=Sqr(((-Nx-Nz)/Ny)*((-Nx-Nz)/Ny)+2.)
		P1x#=1./P1mod
		P1y#=((-Nx-Nz)/Ny)/P1mod
		P1z#=1./P1mod
	Else 
		P1mod#=Sqr(((-Ny-Nx)/Nz)*((-Ny-Nx)/Nz)+2.)
		P1x#=1./P1mod
		P1y#=1./P1mod
		P1z#=((-Nx-Ny)/Nz)/P1mod
	End If
		
	P2x#=Ny*P1z-Nz*P1y
	P2y#=Nz*P1x-Nx*P1z
	P2z#=Nx*P1y-Ny*P1x
				
	vP1#=(fichas(ind)\vx*P1x+fichas(ind)\vy*P1y+fichas(ind)\vz*P1z)*CR
	vP2#=(fichas(ind)\vx*P2x+fichas(ind)\vy*P2y+fichas(ind)\vz*P2z)*CR
			
	fichas(ind)\vx#=(vN*Nx+vP1*P1x++vP2*P2x)
	fichas(ind)\vy#=(vN*Ny+vP1*P1y++vP2*P2y)
	fichas(ind)\vz#=(vN*Nz+vP1*P1z++vP2*P2z)
	
	Return vN

End Function


Function CalculaColisionEntreFichas#(ind1%,ind2%)

	Nx#=CollisionNX(fichas(ind1)\o,1)
	Ny#=CollisionNY(fichas(ind1)\o,1)
	Nz#=CollisionNZ(fichas(ind1)\o,1)	
	
	CoEl# = 0.8
	
	;Normal del choque
	; Se intercambian las velocidades normales al choque
	vN_2#=(fichas(ind1)\vx*Nx+fichas(ind1)\vy*Ny+fichas(ind1)\vz*Nz)
	vN_1#=(fichas(ind2)\vx*Nx+fichas(ind2)\vy*Ny+fichas(ind2)\vz*Nz)

	If Nx <>0 
		P1mod#=Sqr(((-Ny-Nz)/Nx)*((-Ny-Nz)/Nx)+2)
		P1x#=((-Ny-Nz)/Nx)/P1mod
		P1y#=1/P1mod
		P1z#=1/P1mod
	Else If Ny <> 0
		P1mod#=Sqr(((-Nx-Nz)/Ny)*((-Nx-Nz)/Ny)+2)
		P1x#=1/P1mod
		P1y#=((-Nx-Nz)/Ny)/P1mod
		P1z#=1/P1mod
	Else 
		P1mod#=Sqr(((-Ny-Nx)/Nz)*((-Ny-Nx)/Nz)+2)
		P1x#=1/P1mod
		P1y#=1/P1mod
		P1z#=((-Nx-Ny)/Nz)/P1mod
	End If
		
	P2x#=Ny*P1z-Nz*P1y
	P2y#=Nz*P1x-Nx*P1z
	P2z#=Nx*P1y-Ny*P1x
				
	vP1_1#=(fichas(ind1)\vx*P1x+fichas(ind1)\vy*P1y+fichas(ind1)\vz*P1z)
	vP2_1#=(fichas(ind1)\vx*P2x+fichas(ind1)\vy*P2y+fichas(ind1)\vz*P2z)
	
	vP1_2#=(fichas(ind2)\vx*P1x+fichas(ind2)\vy*P1y+fichas(ind2)\vz*P1z)
	vP2_2#=(fichas(ind2)\vx*P2x+fichas(ind2)\vy*P2y+fichas(ind2)\vz*P2z)
			
	fichas(ind1)\vx=(vN_1*Nx+vP1_1*P1x++vP2_1*P2x)
	fichas(ind1)\vy=(vN_1*Ny+vP1_1*P1y++vP2_1*P2y)
	fichas(ind1)\vz=(vN_1*Nz+vP1_1*P1z++vP2_1*P2z)
	
	fichas(ind2)\vx=(vN_2*Nx+vP1_2*P1x++vP2_2*P2x)
	fichas(ind2)\vy=(vN_2*Ny+vP1_2*P1y++vP2_2*P2y)
	fichas(ind2)\vz=(vN_2*Nz+vP1_2*P1z++vP2_2*P2z)
	
End Function





;Establece el número de frames por segundo
Function ControlaFPS()
		.ESPERA
		If (MilliSecs() -time0) < mspf Then Goto ESPERA
		time0 = MilliSecs()
End Function



End