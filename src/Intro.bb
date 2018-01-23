;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;								INTRO									 ;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



Graphics3D 800,600,2,2
    
;Goto NOINTRO
	titulo_i = LoadImage("Imagenes/titulo.bmp")
	ScaleImage titulo_i,1.2,1.2
	
	

	logo = LoadImage("Imagenes/logo.jpg")
	

	SetBuffer BackBuffer()


	
	camera = CreateCamera()
	light =  CreateLight(0)
	LightColor light,255,255,0
	PositionEntity light,0,20,0
	RotateEntity light,90,0,0
	CameraFogMode camera,1
	CameraFogRange camera,15,40
	RotateEntity camera,20,-90,0
	PositionEntity camera,-15,5,0
	CameraZoom camera,1
	pivot = CreatePivot()
	EntityParent camera,pivot

   
	cubo = CreateCube()
    cubo_t = LoadTexture("Imagenes/mask.bmp")
    
	ScaleEntity cubo,15,0.2,15
	EntityTexture cubo, cubo_t
	EntityColor cubo,255,125,213
	PositionEntity cubo,0,-3.5,0


	
	PuenteD=LoadMesh("modelos/Puente.3ds")
	RotateEntity PuenteD,-90,-90,0
	ScaleEntity PuenteD,0.5,0.5,0.5
	PositionEntity PuenteD,CajaX,CajaY-0.2,CajaZ+40
	EntityShininess PuenteD,0.9

	PuenteI=LoadMesh("modelos/Puente.3ds")
	RotateEntity PuenteI,-90,-90,0
	ScaleEntity PuenteI,0.5,0.5,0.5
	PositionEntity PuenteI,CajaX,CajaY-0.2,CajaZ-40
	EntityShininess PuenteI,0.9

	Molino=LoadMesh("modelos/molino.3ds")
	ScaleEntity Molino,0.4,0.35,0.35
	RotateEntity Molino,-90,-90,0
	PositionEntity Molino,CajaX+50,CajaY,CajaZ
	EntityShininess Molino,0.9

	Rana=LoadMesh("modelos/rana.3ds")
	ScaleEntity Rana,0.1,0.1,0.1
	RotateEntity Rana,-90,-90,0
	PositionEntity Rana,CajaX+5,CajaY+35,CajaZ
	EntityFX Rana,0
	EntityShininess Rana,0.9
	
	Dim fi.tipoficha(20)
	tp% = 0
	NumFi% = 0
	
		fi(0) = New tipoficha
		fi(0)\o=LoadMesh("modelos/ficha.3ds")
		ScaleEntity fi(0)\o,0.2,0.2,0.2
		HideEntity fi(0)\o
		
		For k = 1 To 20
			fi(k) = New tipoficha
			fi(k)\o = CopyEntity (fi(0)\o)
			HideEntity fi(k)\o
		Next
	
		
		
		

	HideEntity PuenteI
	HideEntity PuenteD
	HideEntity Rana
	HideEntity Molino
	HideEntity cubo
	

	

	DrawImage logo,300,200
	
	Text 330,400,"2012. DIEGO GONZÁLEZ"
	Flip
	RenderWorld
	
	Delay 1000
	
	
	
	
	; Escenario
	ShowEntity cubo
	For k = 1 To 50
		ControlaFPS()
		EntityAlpha cubo,k*0.02
		RenderWorld
		Flip
	Next
	

	
	; Puentes
	ShowEntity PuenteI
	ShowEntity PuenteD
	V# = 3.9
	For k = 0 To 100
		ControlaFPS()	
		V = V - V*0.1
		MoveEntity PuenteI,-V,0,0
		MoveEntity PuenteD,+V,0,0
		RenderWorld
		Flip
	Next
	
	EntityParent PuenteI,cubo
	EntityParent PuenteD,cubo
	
	
	
	;Molino
	ShowEntity Molino
	V# = 20
	For k = 0 To 100
		ControlaFPS()	
		V = V - V*0.1
		PositionEntity Molino,-4+EntityZ(Molino)+V,CajaY,0
		TurnEntity Molino,-V*2,0,0
		RenderWorld
		Flip
	Next
	EntityParent Molino,cubo
	
	;Rana
	V# = 3.92
	ShowEntity Rana
	For k = 0 To 100
		ControlaFPS()	
		V = V - V*0.1
		MoveEntity Rana,0,0,-V
		RenderWorld
		Flip
	Next
	EntityParent Rana,cubo

	cte# = 5
	For k = 0 To 20
		PositionEntity fi(k)\o, 3,0,0
		fi(k)\vy = Rnd(1,2)/cte#
	    fi(k)\vz = Rnd(-1,1)/cte#
	    fi(k)\vx = Rnd(-1,1)/cte#
		EntityColor fi(k)\o, Rnd(255),Rnd(255),Rnd(255)
		ShowEntity fi(k)\o
	Next
	
	; Rotamos todo y lluvia de fichas
	cont = 0
	FlushKeys()
	While Not KeyHit(57)
		
		ControlaFPS()
		cont = cont +1
		For k = 0 To 20
			If (Abs (EntityZ(fi(k)\o)) + Abs (EntityZ(fi(k)\o))+Abs (EntityY(fi(k)\o)) > 10) Then
 				PositionEntity fi(k)\o, 3,0,0
				fi(k)\vy = Rnd(2,3)/cte#
	    		fi(k)\vz = Rnd(-1,1)/cte#
	    		fi(k)\vx = Rnd(-3,-2)/cte#
				EntityColor fi(k)\o, Rnd(255),Rnd(255),Rnd(255)
			End If
	 		MoveEntity fi(k)\o,fi(k)\vx,fi(k)\vy,fi(k)\vz
			fi(k)\vy = fi(k)\vy -0.15/cte
		Next
		TurnEntity pivot,0,2,0
		
		
		RenderWorld
		UpdateWorld
		If cont > 50 Then
			DrawImage titulo_i,160,50
			Color 0,0,0
			Text 280,550,"PULSA ESPACIO PARA CONTINUAR"
		End If
		Flip
	Wend
	
	
	
	
	WaitKey()
	
	FreeEntity Molino
	FreeEntity Rana
	FreeEntity PuenteI
	FreeEntity PuenteD
	FreeEntity Patas
	FreeEntity Fondo
	FreeEntity Caja
	FreeTexture cubo_t
	FreeImage logo
	FreeImage titulo_i
	For k = 1 To 20
			FreeEntity fi(k)\o
	Next

	

Function Muevefi()
		
	For k = 0 To 20
	 	MoveEntity fi(k)\o,fi(k)\vx,fi(k)\vy,fi(k)\vz
		fi(k)\vy = fi(k)\vy - 0.0005
		If (Abs (EntityZ(fi(k)\o)) + Abs (EntityZ(fi(k)\o))+Abs (EntityY(fi(k)\o)) > 10)
			PositionEntity fi(k)\o, 0,2,0
			fi(k)\vy = Rnd(1,5)/cte#
		    fi(k)\vz = Rnd(-1,1)/cte#
		    fi(k)\vx = Rnd(-1,1)/cte#
		End If
	Next

End Function


.NOINTRO