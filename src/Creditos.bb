;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;
;;;;				CRÉDITOS		
;;;;


Function Creditos()

	Graphics3D 800,600,2,2

	camera = CreateCamera()
	light =  CreateLight(0)

	PositionEntity light,0,0,0
	RotateEntity light,90,0,0
	
	CameraFogMode camera,1
	CameraFogRange camera,15,40
	RotateEntity camera,10,-90,0
	PositionEntity camera,-20,10,0
	CameraZoom camera,1
	pivot = CreatePivot()
	EntityParent camera,pivot

	logo = LoadImage("Imagenes/logo.jpg")
	email = LoadImage("Imagenes/email.jpg")

	; Texto
	fuente_t_m = LoadAnimImage ("Imagenes/fuente.bmp",50,50,0,36)
	ScaleImage fuente_t_m,0.5,0.5
	
	Acento = CreateImage(50,60)
	SetBuffer ImageBuffer(Acento)
	Color 255,255,255
	Rect 25,5,15,7
	RotateImage Acento,-30
	ScaleImage Acento,0.5,0.5


	Rana=LoadMesh("Modelos/rana.3ds")
	ScaleEntity Rana,0.1,0.1,0.1
	RotateEntity Rana,-90,-90,0
	PositionEntity Rana,CajaX,CajaY,CajaZ
	EntityFX Rana,0
	EntityShininess Rana,0.9
	
	Dim fi.tipoficha(20)
	tp% = 0
	NumFi% = 0
	
		fi(0) = New tipoficha
		fi(0)\o=LoadMesh("Modelos/ficha.3ds")
		ScaleEntity fi(0)\o,0.2,0.2,0.2
		HideEntity fi(0)\o
		
		For k = 1 To 20
			fi(k) = New tipoficha
			fi(k)\o = CopyEntity (fi(0)\o)
			HideEntity fi(k)\o
		Next

	
	cte# = 5
	For k = 0 To 20
		PositionEntity fi(k)\o, -1,-0.2,0
		fi(k)\vy = Rnd(2,3)/cte#
	    fi(k)\vz = Rnd(-1,1)/cte#
	    fi(k)\vx = Rnd(-3,2)/cte#
		;EntityColor fi(k)\o, Rnd(255),Rnd(255),Rnd(255)
		ShowEntity fi(k)\o
	Next

	sl = False

	cont = 0
	SetBuffer BackBuffer()
	While Not KeyHit(57) Or sl = True
		
	cont = cont + 1	
	DrawImage email,250,560	
		
	If cont < 80 Then ;--------------------------------------
		DrawImage fuente_t_m,225,100,3 ;D
		DrawImage fuente_t_m,250,100,8 ;i
		DrawImage fuente_t_m,275,100,18 ;s
		DrawImage fuente_t_m,300,100,4 ;e
			Rect 330,95,15,3
		DrawImage fuente_t_m,325,100,13 ;n
		DrawImage fuente_t_m,350,100,14 ;o
		;---espacio en blanco---
		DrawImage fuente_t_m,400,100,6 ;g
		DrawImage fuente_t_m,425,100,17 ;r
			DrawImage Acento,455,100
		DrawImage fuente_t_m,450,100,0 ;a
		DrawImage fuente_t_m,475,100,5 ;f
		DrawImage fuente_t_m,500,100,8 ;i
		DrawImage fuente_t_m,525,100,2 ;c
		DrawImage fuente_t_m,550,100,14 ;o
		;--------------------------------------
		Color 255,255,255
		Rect 225,130,350,5
		;--------------------------------------
		DrawImage fuente_t_m,225,200,3 ;D
		DrawImage fuente_t_m,250,200,8 ;i
		DrawImage fuente_t_m,275,200,4 ;e
		DrawImage fuente_t_m,300,200,6 ;g
		DrawImage fuente_t_m,325,200,14 ;o
		;---espacio en blanco---
		DrawImage fuente_t_m,375,200,6 ;G
		DrawImage fuente_t_m,400,200,14 ;o
		DrawImage fuente_t_m,425,200,13 ;n
		DrawImage fuente_t_m,450,200,25 ;z
		DrawImage fuente_t_m,475,200,0 ;a
			DrawImage Acento,475,200
		DrawImage fuente_t_m,500,200,11 ;l
		DrawImage fuente_t_m,525,200,4 ;e
		DrawImage fuente_t_m,550,200,25 ;z
		;--------------------------------------
	
	Else If (cont > 80) And (cont < 160)
		;--------------------------------------
		DrawImage fuente_t_m,250,100,15 ;p
		DrawImage fuente_t_m,275,100,17 ;r
		DrawImage fuente_t_m,300,100,14 ;o
		DrawImage fuente_t_m,325,100,6 ;g
		DrawImage fuente_t_m,350,100,17 ;r
		DrawImage fuente_t_m,375,100,0 ;a
		DrawImage fuente_t_m,400,100,12 ;m
		DrawImage fuente_t_m,425,100,0 ;a
		DrawImage fuente_t_m,450,100,2 ;c
		DrawImage fuente_t_m,475,100,8 ;i
			DrawImage Acento,500,100
		DrawImage fuente_t_m,500,100,14 ;o
		DrawImage fuente_t_m,525,100,13 ;n
		;--------------------------------------
		Color 255,255,255
		Rect 250,130,300,5
		
		;--------------------------------------
		DrawImage fuente_t_m,225,200,3 ;D
		DrawImage fuente_t_m,250,200,8 ;i
		DrawImage fuente_t_m,275,200,4 ;e
		DrawImage fuente_t_m,300,200,6 ;g
		DrawImage fuente_t_m,325,200,14 ;o
		;---espacio en blanco---
		DrawImage fuente_t_m,375,200,6 ;G
		DrawImage fuente_t_m,400,200,14 ;o
		DrawImage fuente_t_m,425,200,13 ;n
		DrawImage fuente_t_m,450,200,25 ;z
		DrawImage fuente_t_m,475,200,0 ;a
			DrawImage Acento,475,200
		DrawImage fuente_t_m,500,200,11 ;l
		DrawImage fuente_t_m,525,200,4 ;e
		DrawImage fuente_t_m,550,200,25 ;z
		;--------------------------------------
	
	Else If (cont > 160) And (cont < 240)
		DrawImage logo,300,100
		;--------------------------------------
		DrawImage fuente_t_m,350,300,28 ;2
		DrawImage fuente_t_m,375,300,26 ;0
		DrawImage fuente_t_m,400,300,27 ;1
		DrawImage fuente_t_m,425,300,28 ;2
		;--------------------------------------
	Else If (cont > 250)
		sl = True
	End If
		
		
		
		
		ControlaFPS()

		For k = 0 To 20
			If (Abs (EntityZ(fi(k)\o)) + Abs (EntityZ(fi(k)\o))+Abs (EntityY(fi(k)\o)) > 10) Then
 				PositionEntity fi(k)\o, -1,-0.2,0
				fi(k)\vy = Rnd(2,3)/cte#
	    		fi(k)\vz = Rnd(-1,1)/cte#
	    		fi(k)\vx = Rnd(-3,-2)/cte#
				;EntityColor fi(k)\o, Rnd(255),Rnd(255),Rnd(255)
			End If
	 		MoveEntity fi(k)\o,fi(k)\vx,fi(k)\vy,fi(k)\vz
			fi(k)\vy = fi(k)\vy -0.15/cte
		Next
		TurnEntity pivot,0,2,0
		
		Flip
		RenderWorld
		UpdateWorld
		
	Wend



	FreeEntity Rana
	FreeEntity camera
	FreeEntity Ficha
	FreeEntity ligth
	FreeImage logo
	FreeImage email
	FreeImage fuente_t_m

	For k = 1 To 20
			FreeEntity fi(k)\o
	Next

 

End Function





