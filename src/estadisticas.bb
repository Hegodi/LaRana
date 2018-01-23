; estadíscticas


Function Estadisticas()
	Cls
	fondo = LoadImage ("Imagenes/fondo.jpg")
	
	fon_su = CreateImage (800,100)
	fon_in = CreateImage (800,100)
	fon_iz = CreateImage (100,600)
	fon_de = CreateImage (100,600)
	
	SetBuffer ImageBuffer(fon_su)
		DrawImage fondo,0,0
		
	SetBuffer ImageBuffer(fon_in)
		DrawImage fondo,0,-500
		
	SetBuffer ImageBuffer(fon_iz)
		DrawImage fondo,0,0
		
	SetBuffer ImageBuffer(fon_de)
		DrawImage fondo,-700,0
		
	FreeImage fondo

	
	
	fuente_est = LoadAnimImage ("Imagenes/fuente.bmp",50,50,0,36)
	ScaleImage fuente_est,0.3,0.3
	
	img_est = CreateImage (600,1100)
	u% = 0
	d% = 0
	c% = 0
	m% = 0
	

	SetBuffer ImageBuffer(img_est)

		Color 255,255,255
		;--------------------------------------
		DrawImage fuente_m_p,100,0,3 ;D
		DrawImage fuente_m_p,125,0,4 ;e
		DrawImage fuente_m_p,150,0,18 ;s
		DrawImage fuente_m_p,175,0,0 ;a
		DrawImage fuente_m_p,200,0,5 ;f
		DrawImage fuente_m_p,225,0,8 ;i
		DrawImage fuente_m_p,250,0,14 ;o
		DrawImage fuente_m_p,275,0,18 ;s
		;---espacio en blanco---
		DrawImage fuente_m_p,325,0,9 ;j
		DrawImage fuente_m_p,350,0,20 ;u
		DrawImage fuente_m_p,375,0,6 ;g
		DrawImage fuente_m_p,400,0,0 ;a
		DrawImage fuente_m_p,425,0,3 ;d
		DrawImage fuente_m_p,450,0,14 ;o
		DrawImage fuente_m_p,475,0,18 ;s
		;--------------------------------------
			;--------------------------------------
			DrawImage fuente_est,120,80,13 ;n
			DrawImage fuente_est,135,80,8 ;i
			DrawImage fuente_est,150,80,21 ;v
			DrawImage fuente_est,165,80,4 ;e
			DrawImage fuente_est,180,80,11 ;l
			;---espacio en blanco---
			DrawImage fuente_est,210,80,26 ;0
			;--------------------------------------
			Rect 235,90,195,2
			c = desafios_0_jug/100 
			d = desafios_0_jug/10 - c*10
			u = desafios_0_jug - d*10 - c*100 
			;--------------------------------------
			DrawImage fuente_est,440,80,26+c ;0
			DrawImage fuente_est,455,80,26+d ;0
			DrawImage fuente_est,470,80,26+u;0
			;--------------------------------------
			
			;--------------------------------------
			DrawImage fuente_est,120,110,13 ;n
			DrawImage fuente_est,135,110,8 ;i
			DrawImage fuente_est,150,110,21 ;v
			DrawImage fuente_est,165,110,4 ;e
			DrawImage fuente_est,180,110,11 ;l
			;---espacio en blanco---
			DrawImage fuente_est,210,110,27 ;1
			;--------------------------------------
			Rect 235,120,195,2
			c = desafios_1_jug/100 
			d = desafios_1_jug/10 - c*10
			u = desafios_1_jug - d*10 - c*100
			;--------------------------------------
			DrawImage fuente_est,440,110,26+c ;0
			DrawImage fuente_est,455,110,26+d ;0
			DrawImage fuente_est,470,110,26+u ;0
			;--------------------------------------
			
			;--------------------------------------
			DrawImage fuente_est,120,140,13 ;n
			DrawImage fuente_est,135,140,8 ;i
			DrawImage fuente_est,150,140,21 ;v
			DrawImage fuente_est,165,140,4 ;e
			DrawImage fuente_est,180,140,11 ;l
			;---espacio en blanco---
			DrawImage fuente_est,210,140,28 ;2
			;--------------------------------------
			Rect 235,150,195,2
			c = desafios_2_jug/100 
			d = desafios_2_jug/10 - c*10
			u = desafios_2_jug - d*10 - c*100
			;--------------------------------------
			DrawImage fuente_est,440,140,26+c ;0
			DrawImage fuente_est,455,140,26+d ;0
			DrawImage fuente_est,470,140,26+u ;0
			;--------------------------------------
			
			;--------------------------------------
			DrawImage fuente_est,120,170,13 ;n
			DrawImage fuente_est,135,170,8 ;i
			DrawImage fuente_est,150,170,21 ;v
			DrawImage fuente_est,165,170,4 ;e
			DrawImage fuente_est,180,170,11 ;l
			;---espacio en blanco---
			DrawImage fuente_est,210,170,29 ;3
			;--------------------------------------
			Rect 235,180,195,2
			c = desafios_3_jug/100 
			d = desafios_3_jug/10 -c*10
			u = desafios_3_jug - d*10 - c*100
			;--------------------------------------
			DrawImage fuente_est,440,170,26+c ;0
			DrawImage fuente_est,455,170,26+d ;0
			DrawImage fuente_est,470,170,26+u ;0
			;--------------------------------------


		;--------------------------------------
		DrawImage fuente_m_p,100,250,3 ;D
		DrawImage fuente_m_p,125,250,4 ;e
		DrawImage fuente_m_p,150,250,18 ;s
		DrawImage fuente_m_p,175,250,0 ;a
		DrawImage fuente_m_p,200,250,5 ;f
		DrawImage fuente_m_p,225,250,8 ;i
		DrawImage fuente_m_p,250,250,14 ;o
		DrawImage fuente_m_p,275,250,18 ;s
		;---espacio en blanco---
		DrawImage fuente_m_p,325,250,6 ;g
		DrawImage fuente_m_p,350,250,0 ;a
		DrawImage fuente_m_p,375,250,13 ;n
		DrawImage fuente_m_p,400,250,0 ;a
		DrawImage fuente_m_p,425,250,3 ;d
		DrawImage fuente_m_p,450,250,14 ;o
		DrawImage fuente_m_p,475,250,18 ;s
		;--------------------------------------
			;--------------------------------------
			DrawImage fuente_est,120,330,13 ;n
			DrawImage fuente_est,135,330,8 ;i
			DrawImage fuente_est,150,330,21 ;v
			DrawImage fuente_est,165,330,4 ;e
			DrawImage fuente_est,180,330,11 ;l
			;---espacio en blanco---
			DrawImage fuente_est,210,330,26 ;0
			;--------------------------------------
			Rect 235,340,195,2
			c = desafios_0_gan/100 
			d = desafios_0_gan/10 - c*10
			u = desafios_0_gan - d*10 - c*100
			;--------------------------------------
			DrawImage fuente_est,440,330,26+c ;0
			DrawImage fuente_est,455,330,26+d ;0
			DrawImage fuente_est,470,330,26+u ;0
			;--------------------------------------
			
			;--------------------------------------
			DrawImage fuente_est,120,360,13 ;n
			DrawImage fuente_est,135,360,8 ;i
			DrawImage fuente_est,150,360,21 ;v
			DrawImage fuente_est,165,360,4 ;e
			DrawImage fuente_est,180,360,11 ;l
			;---espacio en blanco---
			DrawImage fuente_est,210,360,27 ;1
			;--------------------------------------
			Rect 235,370,195,2
			c = desafios_1_gan/100 
			d = desafios_1_gan/10 - c*10
			u = desafios_1_gan - d*10 - c*100
			;--------------------------------------
			DrawImage fuente_est,440,360,26+c ;0
			DrawImage fuente_est,455,360,26+d ;0
			DrawImage fuente_est,470,360,26+u ;0
			;--------------------------------------
			
			;--------------------------------------
			DrawImage fuente_est,120,390,13 ;n
			DrawImage fuente_est,135,390,8 ;i
			DrawImage fuente_est,150,390,21 ;v
			DrawImage fuente_est,165,390,4 ;e
			DrawImage fuente_est,180,390,11 ;l
			;---espacio en blanco---
			DrawImage fuente_est,210,390,28 ;2
			;--------------------------------------
			Rect 235,400,195,2
			c = desafios_2_gan/100 
			d = desafios_2_gan/10 - c*10
			u = desafios_2_gan - d*10 - c*100
			;--------------------------------------
			DrawImage fuente_est,440,390,26+c ;0
			DrawImage fuente_est,455,390,26+d ;0
			DrawImage fuente_est,470,390,26+u ;0
			;--------------------------------------
			
			;--------------------------------------
			DrawImage fuente_est,120,420,13 ;n
			DrawImage fuente_est,135,420,8 ;i
			DrawImage fuente_est,150,420,21 ;v
			DrawImage fuente_est,165,420,4 ;e
			DrawImage fuente_est,180,420,11 ;l
			;---espacio en blanco---
			DrawImage fuente_est,210,420,29 ;3
			;--------------------------------------
			Rect 235,430,195,2
			c = desafios_3_gan/100 
			d = desafios_3_gan/10 - c*10
			u = desafios_3_gan - d*10 - c*100
			;--------------------------------------
			DrawImage fuente_est,440,420,26+c ;0
			DrawImage fuente_est,455,420,26+d ;0
			DrawImage fuente_est,470,420,26+u ;0
			;--------------------------------------
		
		;--------------------------------------
		DrawImage fuente_m_p,100,500,17 ;R
		DrawImage fuente_m_p,125,500,4 ;e
		DrawImage fuente_m_p,150,500,2 ;c
		DrawImage fuente_m_p,175,500,14 ;o
		DrawImage fuente_m_p,200,500,17 ;r
		DrawImage fuente_m_p,225,500,3 ;d
		;---espacio en blanco---
		DrawImage fuente_m_p,275,500,3 ;d
		DrawImage fuente_m_p,300,500,4 ;e
		;---espacio en blanco---
		DrawImage fuente_m_p,350,500,19 ;t
		DrawImage fuente_m_p,375,500,8 ;i
		DrawImage fuente_m_p,400,500,17 ;r
		DrawImage fuente_m_p,425,500,0 ;a
		DrawImage fuente_m_p,450,500,3 ;d
		DrawImage fuente_m_p,475,500,0 ;a
		;--------------------------------------

			;--------------------------------------
			DrawImage fuente_est,120,580,17 ;r
			DrawImage fuente_est,135,580,0 ;a
			DrawImage fuente_est,150,580,13 ;n
			DrawImage fuente_est,165,580,0 ;a
			DrawImage fuente_est,180,580,18 ;s
			;--------------------------------------
			Rect 205,590,225,2
			c = ranas_max/100 
			d = ranas_max/10 - c*10
			u = ranas_max - d*10 - c*100
			;--------------------------------------
			DrawImage fuente_est,440,580,26+c ;0
			DrawImage fuente_est,455,580,26+d ;0
			DrawImage fuente_est,470,580,26+u ;0
			;--------------------------------------
			
			;--------------------------------------
			DrawImage fuente_est,120,610,15 ;p
			DrawImage fuente_est,135,610,20 ;u
			DrawImage fuente_est,150,610,4 ;e
			DrawImage fuente_est,165,610,13 ;n
			DrawImage fuente_est,180,610,19 ;t
			DrawImage fuente_est,195,610,4 ;e
			DrawImage fuente_est,210,610,18 ;s
			;--------------------------------------
			Rect 235,620,195,2
			c = puentes_max/100 
			d = puentes_max/10 - c*10
			u = puentes_max - d*10 - c*100
			;--------------------------------------
			DrawImage fuente_est,440,610,26+c ;0
			DrawImage fuente_est,455,610,26+d ;0
			DrawImage fuente_est,470,610,26+u ;0
			;--------------------------------------
			
			;--------------------------------------
			DrawImage fuente_est,120,640,12 ;m
			DrawImage fuente_est,135,640,14 ;o
			DrawImage fuente_est,150,640,11 ;l
			DrawImage fuente_est,165,640,8 ;i
			DrawImage fuente_est,180,640,13 ;n
			DrawImage fuente_est,195,640,14 ;o
			DrawImage fuente_est,210,640,18 ;s
			;--------------------------------------
			Rect 235,650,195,2
			c = molinos_max/100 
			d = molinos_max/10 - c*10
			u = molinos_max - d*10 - c*100
			;--------------------------------------
			DrawImage fuente_est,440,640,26+c ;0
			DrawImage fuente_est,455,640,26+d ;0
			DrawImage fuente_est,470,640,26+u ;0
			;--------------------------------------
			
			;--------------------------------------
			DrawImage fuente_est,120,670,15 ;P
			DrawImage fuente_est,135,670,20 ;u
			DrawImage fuente_est,150,670,13 ;n
			DrawImage fuente_est,165,670,19 ;t
			DrawImage fuente_est,180,670,14 ;o
			DrawImage fuente_est,195,670,18 ;s
			;--------------------------------------
			Rect 220,680,210,2
			c = puntos_tir_max/100 
			d = puntos_tir_max/10 - c*10
			u = puntos_tir_max - d*10 - c*100
			;--------------------------------------
			DrawImage fuente_est,440,670,26+c ;0
			DrawImage fuente_est,455,670,26+d ;0
			DrawImage fuente_est,470,670,26+u ;0
			;--------------------------------------
			
		;--------------------------------------
		DrawImage fuente_m_p,50,750,11 ;l
		DrawImage fuente_m_p,75,750,14 ;o
		DrawImage fuente_m_p,100,750,6 ;g
		DrawImage fuente_m_p,125,750,17 ;r
		DrawImage fuente_m_p,150,750,14 ;o
		DrawImage fuente_m_p,175,750,18 ;s
		;---espacio en blanco---
		DrawImage fuente_m_p,225,750,3 ;d
		DrawImage fuente_m_p,250,750,4 ;e
		DrawImage fuente_m_p,275,750,18 ;s
		DrawImage fuente_m_p,300,750,1 ;b
		DrawImage fuente_m_p,325,750,11 ;l
		DrawImage fuente_m_p,350,750,14 ;o
		DrawImage fuente_m_p,375,750,16 ;q
		DrawImage fuente_m_p,400,750,20 ;u
		DrawImage fuente_m_p,425,750,4 ;e
		DrawImage fuente_m_p,450,750,0 ;a
		DrawImage fuente_m_p,475,750,3 ;d
		DrawImage fuente_m_p,500,750,14 ;o
		DrawImage fuente_m_p,525,750,18 ;s
		;--------------------------------------
			LL% = 0
			For k = 1 To 8 
				If Logros_desbloqueados(k) = True Then LL = LL +1 
			Next
			;--------------------------------------
			DrawImage fuente_est,255,830,26+LL ;0
			;---espacio en blanco---
			DrawImage fuente_est,285,830,3 ;d
			DrawImage fuente_est,300,830,4 ;e
			;---espacio en blanco---
			DrawImage fuente_est,330,830,26+8 ;8
			;--------------------------------------
			
		
		;--------------------------------------
		DrawImage fuente_m_p,100,910,12 ;m
		DrawImage fuente_m_p,125,910,4 ;e
		DrawImage fuente_m_p,150,910,9 ;j
		DrawImage fuente_m_p,175,910,14 ;o
		DrawImage fuente_m_p,200,910,17 ;r
		;---espacio en blanco---
		DrawImage fuente_m_p,250,910,15 ;p
		DrawImage fuente_m_p,275,910,20 ;u
		DrawImage fuente_m_p,300,910,13 ;n
		DrawImage fuente_m_p,325,910,19 ;t
		DrawImage fuente_m_p,350,910,20 ;u
		DrawImage fuente_m_p,375,910,0 ;a
		DrawImage fuente_m_p,400,910,2 ;c
		DrawImage fuente_m_p,425,910,8 ;i
		DrawImage fuente_m_p,450,910,14 ;o
		DrawImage fuente_m_p,475,910,13 ;n
			m = puntos_par_max/1000 
			c = puntos_par_max/100 -m*10 
			d = puntos_par_max/10 - c*10 -m*100
			u = puntos_par_max - d*10 - c*100 -m*100
			;--------------------------------------
			DrawImage fuente_est,270,990,26+m ;0
			DrawImage fuente_est,285,990,26+c ;0
			DrawImage fuente_est,300,990,26+d ;0
			DrawImage fuente_est,315,990,26+u ;0
			;--------------------------------------
		

	SetBuffer BackBuffer()

	Scrll = 600	
	
	
	While Not KeyHit(1)
		ControlaFPS()

		Cls
		DrawImage img_est,100,Scrll
		DrawImage fon_su,0,0
		DrawImage fon_in,0,500
		DrawImage fon_iz,0,0
		DrawImage fon_de,700,0
		Scrll = Scrll - 3
		If Scrll < -900 Then Scrll = 600
		;--------------------------------------
		DrawImage fuente_m_p,306,540,21 ;v
		DrawImage fuente_m_p,341,540,14 ;o
		DrawImage fuente_m_p,376,540,11 ;l
		DrawImage fuente_m_p,411,540,21 ;v
		DrawImage fuente_m_p,446,540,4 ;e
		DrawImage fuente_m_p,481,540,17 ;r
		;--------------------------------------

		Flip
		
		If (MouseX()>316) And (MouseX() < 514) And (MouseY() >540) And (MouseY()<575) Then
			Text 492,115, "Volver al menú principal"
			If MouseHit(1) Then
				Exit
			EndIf 
		End If

		
	Wend


	FreeImage fon_su
	FreeImage fon_in
	FreeImage fon_iz
	FreeImage fon_de
	FreeImage fuente_est
	FreeImage img_est

End Function