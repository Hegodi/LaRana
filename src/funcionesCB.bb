;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;
;;;;;	Funciones de carga y eliniacion de objetos


Function Crea_Fichas()

    If TipoFichas = 2 Then fichas_tex = LoadAnimTexture("Imagenes/fuego.bmp",1+2,80,80,0,5)
	
	For k=1 To Num_fichas
    	fichas(k) = New tipoficha
		fichas(k)\o=LoadMesh("modelos/ficha.3ds")
		
		; fichas de fuego
		If TipoFichas = 2 Then 
			fichas_sprite(k) = CreateSprite()			
			PositionEntity fichas_sprite(k),0,1,-3
			EntityTexture fichas_sprite(k),fichas_tex,0
			EntityParent fichas_sprite(k),fichas(k)\o
		End If
		
		
		ScaleEntity fichas(k)\o,0.2,0.25,0.2
		EntityRadius fichas(k)\o,0.3
		EntityColor fichas(k)\o,100,100,100
		PositionEntity fichas(k)\o,-1000,0,0
		EntityType fichas(k)\o,C_ficha
		If TipoFichas = 1 Then
			A = Rand (1,5)
			Select A
				Case 1	EntityColor fichas(k)\o,255,000,000
				Case 2	EntityColor fichas(k)\o,000,255,000
				Case 3	EntityColor fichas(k)\o,000,000,255
				Case 4	EntityColor fichas(k)\o,000,255,255
				Case 5	EntityColor fichas(k)\o,255,255,000
			End Select
		End If
	
	fichas(k)\vx=0
	fichas(k)\vy=0
	fichas(k)\vz=0
	
	Next
	
End Function


;Borra Todos los objetos tipo ficha
Function Borra_Fichas()
   	If TipoFichas = 2 Then FreeTexture(fichas_tex)
	
	For k=1 To Num_Fichas
   		FreeEntity(fichas(k)\o)
   		Delete fichas(k)
		If TipoFichas = 2 Then FreeEntity(fichas_sprite(k))
  	Next

End Function


;Borra los objetos tipo ficha por encima del fondo
Function Borra_Fichas_Arriba()
   	For k=1 To Num_Fichas
   		Limite#=CajaY-0.5
		If EntityY(fichas(k)\o)>Limite Or EntityY(fichas(k)\o)<-7.
			FreeEntity(fichas(k)\o)
   			Delete fichas(k)
			puntos_fichas(k) = -1
		Else
			puntos_fichas(k) = -2
		EndIf
		
  	Next

End Function


;Borra fichas con puntos
Function Borra_Fichas_Puntos()
   	For k=1 To Num_Fichas
		If puntos_fichas(k)> 0 Then
			FreeEntity(fichas(k)\o)
   			Delete fichas(k)
			puntos_fichas(k) = 0
		EndIf
		
  	Next

	If TipoFichas = 2 Then FreeTexture(fichas_tex)

End Function


;Carga en memoria la rana completa

;-----------------------------------------------------------------------------------------------

Function Carga_Rana()

	Caja=LoadMesh("modelos/RanaCaja.3ds")
	RotateEntity Caja,-90,-90,0
	ScaleEntity Caja,0.5,0.5,0.5
	PositionEntity Caja,CajaX,CajaY,CajaZ

	Bisagra = CreatePivot()
	PositionEntity Bisagra,CajaX,CajaY-0.5,CajaZ+9.8


	Fondo=LoadMesh("modelos/Fondo.3ds")
	RotateEntity Fondo,-90,-90,0
	ScaleEntity Fondo,0.5,0.5,0.5
	PositionEntity Fondo,CajaX,CajaY+0.1,CajaZ

	Patas=LoadMesh("modelos/RanaPatas.3ds")
	RotateEntity Patas,-90,0,0
	ScaleEntity Patas,0.5,0.5,0.5
	PositionEntity Patas,CajaX,CajaY+1.7,CajaZ-0.5

	
	
	PuenteD=LoadMesh("modelos/Puente.3ds")
	RotateEntity PuenteD,-90,-90,0
	ScaleEntity PuenteD,0.5,0.5,0.5
	PositionEntity PuenteD,CajaX-4,CajaY-0.2,CajaZ+4.8
	EntityShininess PuenteD,0.9

	PuenteI=LoadMesh("modelos/Puente.3ds")
	RotateEntity PuenteI,-90,-90,0
	ScaleEntity PuenteI,0.5,0.5,0.5
	PositionEntity PuenteI,CajaX-4,CajaY-0.2,CajaZ-4.8
	EntityShininess PuenteI,0.9
	If TipoPuente = 1 Then
		Puentes_tex = CreateTexture(256,256,1)
		SetBuffer TextureBuffer(Puentes_tex)
		Color 0,210,255
		Rect 0,0,255,255
		SetBuffer BackBuffer()
		EntityTexture PuenteI,Puentes_tex
		EntityTexture PuenteD,Puentes_tex
		EntityAlpha PuenteI,0.5
		EntityAlpha PuenteD,0.5
		EntityShininess PuenteI,0.5
		EntityShininess PuenteD,0.5
	End If

	Molino=LoadMesh("modelos/molino.3ds")
	ScaleEntity Molino,0.4,0.35,0.35
	RotateEntity Molino,-90,-90,0
	PositionEntity Molino,CajaX-4.75,CajaY,CajaZ
	EntityShininess Molino,0.9
	If TipoMolino = 2 Then
		HideEntity Molino
	End If

	Rana=LoadMesh("modelos/rana.3ds")
	ScaleEntity Rana,0.1,0.1,0.1
	RotateEntity Rana,-90,-90,0
	PositionEntity Rana,CajaX+1.1,CajaY-0.1,CajaZ
	EntityFX Rana,0
	EntityShininess Rana,0.9
	If TipoRana=1
		Rana_tex = CreateTexture(128,128,1)
		SetBuffer TextureBuffer(Rana_tex)
		EntityShininess Rana,0.4
		Color 255,210,2
		Rect 0,0,255,255
		SetBuffer BackBuffer()
		EntityTexture Rana,Rana_tex
		
	Else If TipoRana = 2 Then
		HideEntity Rana
	End If

	EntityParent Caja,Bisagra
		EntityParent Rana,Caja
		EntityParent Molino,Caja
		EntityParent PuenteI,Caja
		EntityParent PuenteD,Caja
		EntityParent Fondo,Caja

	EntityType Patas,C_madera
	EntityType PuenteI,C_metal
	EntityType PuenteD,C_metal
	EntityType Rana,C_metal
	EntityType Caja,C_madera
	EntityType Molino,C_molino

	RotateEntity Caja,-90,0,0
	
End Function

;Elimina los objetos que componen la rana
Function Borra_Rana()

	FreeEntity Molino
	FreeEntity Rana
	FreeEntity PuenteI
	FreeEntity PuenteD
	FreeEntity Patas
	FreeEntity Fondo
	FreeEntity Caja
	
	If TipoRana = 1 Then FreeTexture(Rana_tex)
	If TipoPuente = 1 Then FreeTexture (Puentes_tex)

End Function


;Carga la mano y el puntero
Function Crea_Grupo_Mano()

    ;Mano
	Mano_t=LoadTexture ("modelos/mano.bmp")
	Mano=LoadMD2("modelos/mano.md2")
	ScaleEntity Mano,0.2,0.2,0.2
	;EntityColor Mano, 220,175,128
	EntityTexture Mano,Mano_t
	EntityFX Mano,2
    ;Punto de Mira
	puntero=LoadMesh("modelos/puntero.3ds")
   	ScaleEntity puntero,0.2,0.2,0.2
   	RotateEntity puntero,-15,0,0
   	A_t=LoadTexture("Texturas/visor.bmp",1)
   	EntityTexture puntero,A_t

    Grupo_Mano=CreatePivot()

End Function


;Borra la mano y el puntero
Function Borra_Grupo_Mano()
	FreeEntity Mano
	FreeEntity Mano_t
	FreeEntity puntero
	FreeEntity A_t
End Function


;Carga Interfaz
Function Carga_Interfaz()
	med_fuer = LoadImage("Imagenes/fuerza.bmp  ")
	
	ind_fuer = LoadImage("Imagenes/ficha.bmp")
	
	interfaz1 = LoadImage("Imagenes/interfaz1.bmp")
	

	
	fuenteIp = LoadAnimImage("Imagenes/fuente.bmp",50,50,0,36)
	ScaleImage fuenteIp,0.4,0.4
	
	fuenteIg = LoadAnimImage("Imagenes/fuente.bmp",50,50,0,36)
	
	Interfaz = CreateImage(800,600)
	SetBuffer (ImageBuffer(Interfaz))
		DrawImage interfaz1,500,120
		DrawImage med_fuer ,70,200
		;DrawImage ind_fuer,100,235-(75*(F-6)) ;520 ;300
	
		;Jugador
		;--------------------------------------
		DrawImage fuenteIp,585,535,9 ;j
		DrawImage fuenteIp,603,535,20 ;u
		DrawImage fuenteIp,621,535,6 ;g
		DrawImage fuenteIp,639,535,0 ;a
		DrawImage fuenteIp,657,535,3 ;d
		DrawImage fuenteIp,675,535,14 ;o
		DrawImage fuenteIp,693,535,17 ;r
		;--------------------------------------

	
		;Ronda
		;--------------------------------------
		DrawImage fuenteIp,610,385,17 ;r
		DrawImage fuenteIp,628,385,14 ;o
		DrawImage fuenteIp,646,385,13 ;n
		DrawImage fuenteIp,664,385,3 ;d
		DrawImage fuenteIp,682,385,0 ;a
		;--------------------------------------
	

		;fichas
		;--------------------------------------
		DrawImage fuenteIp,570,420,5 ;f
		DrawImage fuenteIp,588,420,8 ;i
		DrawImage fuenteIp,606,420,2 ;c
		DrawImage fuenteIp,624,420,7 ;h
		DrawImage fuenteIp,642,420,0 ;a
		DrawImage fuenteIp,660,420,18 ;s
		;--------------------------------------
		
		;Puntuación otros jugadores
		;--------------------------------------
		For k = 1 To num_jug
			DrawImage fuenteIp,662,120+k*50,9 ;j
			DrawImage fuenteIp,677,120+k*50,26+k ;1
		Next
		
		
	SetBuffer BackBuffer()
		
End Function


;Borra interfaz
Function Borra_Interfaz()
	FreeImage med_fuer
	FreeImage ind_fuer
	FreeImage interfaz1
	FreeImage fuenteIp
	FreeImage fuenteIg
End Function

;Crea el escenario
Function Crea_Escenario()

	Select escenario_actual
		Case 0 
			Escenario_0()
		Case 1 
			Escenario_1()
		Case 2 
			Escenario_2()
		Case 3 
			Escenario_3()
		Case 4 
			Escenario_4()
	End Select

End Function

;Borra el escenario
Function Borra_Escenario()
	
	Select escenario_actual
		Case 0 
			Borra_Escenario_0()
		Case 1 
			Borra_Escenario_1()
		Case 2 
			Borra_Escenario_2()
		Case 3 
			Borra_Escenario_3()
		Case 4 
			Borra_Escenario_4()
	End Select

End Function


; carga los sonidos
Function carga_sonidos()
	son_mad = LoadSound("sonidos/madera.wav")
	
	son_met = LoadSound("sonidos/metal.wav")
	
End Function

; carga los sonidos
Function borra_sonidos()
	FreeSound son_mad
	FreeSound son_met
End Function