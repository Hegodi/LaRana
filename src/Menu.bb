;*******************************************************************************************************************
;*****											MENÚ															****
;*******************************************************************************************************************

;Estadisticas:
;	- Partidas jugadas
;	- Logros desbloqueados
;	- Victorias Nivel 0
;	- Victorias Nivel 1
;	- Vinctorias Nivel 2
;   - Victorias Nivel 3

Carga_opc()
Carga_log()

.INICIO

Graphics 800,600,2,2


; Calcula el nivel máximo
If desafios_2_gan > 0 Then	
	NivelMax = 3
Else If desafios_1_gan > 0 Then
	NivelMax = 2
Else If desafios_0_gan > 0 Then
	NivelMax = 1
Else
	NivelMax = 0
End If
		

;Carga de datos

fondo_p = LoadImage ("Imagenes/Menu.jpg")
fondo = LoadImage ("Imagenes/fondo.jpg")

MaskImage fondo_p, 255,255,255
fuente_m = LoadAnimImage ("Imagenes/fuente.bmp",50,50,0,36)
fuente_m_p = LoadAnimImage ("Imagenes/fuente.bmp",50,50,0,36)
ScaleImage fuente_m_p,0.7,0.7

bloq_img = CreateImage(150,150)
	SetBuffer ImageBuffer(bloq_img)
	For i = 0 To 5
		For j = 0 To 5
			C = Rnd(255)
			Color C,C,C
			Rect i*25,j*25,25,25
		Next
	Next


fondo_g = CreateImage(800,400)
SetBuffer ImageBuffer(fondo_g)
	
;--------------------------------------
DrawImage fuente_m_p,100,00,18 ;s
DrawImage fuente_m_p,135,00,14 ;o
DrawImage fuente_m_p,170,00,13 ;n
DrawImage fuente_m_p,205,00,8 ;i
DrawImage fuente_m_p,240,00,3 ;d
DrawImage fuente_m_p,275,00,14 ;o
;--------------------------------------

;--------------------------------------
DrawImage fuente_m_p,100,70,21 ;V
DrawImage fuente_m_p,135,70,8 ;i
DrawImage fuente_m_p,170,70,18 ;s
DrawImage fuente_m_p,205,70,19 ;t
DrawImage fuente_m_p,240,70,0 ;a
;---espacio en blanco--
DrawImage fuente_m_p,310,70,0 ;a
DrawImage fuente_m_p,345,70,20 ;u
DrawImage fuente_m_p,380,70,23 ;x
;--------------------------------------

;--------------------------------------
DrawImage fuente_m_p,100,140,5 ;f
DrawImage fuente_m_p,135,140,8 ;i
DrawImage fuente_m_p,170,140,2 ;c
DrawImage fuente_m_p,205,140,7 ;h
DrawImage fuente_m_p,240,140,0 ;a
DrawImage fuente_m_p,275,140,18 ;s
;--------------------------------------

;--------------------------------------
DrawImage fuente_m_p,100,210,17 ;r
DrawImage fuente_m_p,135,210,14 ;o
DrawImage fuente_m_p,170,210,13 ;n
DrawImage fuente_m_p,205,210,3 ;d
DrawImage fuente_m_p,240,210,0 ;a
DrawImage fuente_m_p,275,210,18 ;s
;--------------------------------------

;--------------------------------------
DrawImage fuente_m_p,100,280,4 ;e
DrawImage fuente_m_p,135,280,18 ;s
DrawImage fuente_m_p,170,280,2 ;c
DrawImage fuente_m_p,205,280,4 ;e
DrawImage fuente_m_p,240,280,13 ;n
DrawImage fuente_m_p,275,280,0 ;a
DrawImage fuente_m_p,310,280,17 ;r
DrawImage fuente_m_p,345,280,8 ;i
DrawImage fuente_m_p,380,280,14 ;o
;--------------------------------------
	
boton = LoadAnimImage("Imagenes/boton.bmp",50,50,0,4)
;fondo opciones logros
fondo_l = CreateImage(800,400)
SetBuffer ImageBuffer(fondo_l)

;--------------------------------------
DrawImage fuente_m_p,100,20,17 ;r
DrawImage fuente_m_p,135,20,0 ;a
DrawImage fuente_m_p,170,20,13 ;n
DrawImage fuente_m_p,205,20,0 ;a
;--------------------------------------

;--------------------------------------
DrawImage fuente_m_p,100,110,12 ;m
DrawImage fuente_m_p,135,110,14 ;o
DrawImage fuente_m_p,170,110,11 ;l
DrawImage fuente_m_p,205,110,8 ;i
DrawImage fuente_m_p,240,110,13 ;n
DrawImage fuente_m_p,275,110,8 ;i
DrawImage fuente_m_p,310,110,11 ;l
DrawImage fuente_m_p,345,110,11 ;l
DrawImage fuente_m_p,380,110,14 ;o
;--------------------------------------

;--------------------------------------
DrawImage fuente_m_p,100,200,15 ;p
DrawImage fuente_m_p,135,200,20 ;u
DrawImage fuente_m_p,170,200,4 ;e
DrawImage fuente_m_p,205,200,13 ;n
DrawImage fuente_m_p,240,200,19 ;t
DrawImage fuente_m_p,275,200,4 ;e
;--------------------------------------

;--------------------------------------
DrawImage fuente_m_p,100,290,5 ;f
DrawImage fuente_m_p,135,290,8 ;i
DrawImage fuente_m_p,170,290,2 ;c
DrawImage fuente_m_p,205,290,7 ;h
DrawImage fuente_m_p,240,290,0 ;a
DrawImage fuente_m_p,275,290,18 ;s
;--------------------------------------
Color 1,1,1
Rect 600,0,80,80
Rect 600,90,80,80
Rect 600,180,80,80
Rect 600,270,80,80






Acento = CreateImage(50,60)
	SetBuffer ImageBuffer(Acento)
	Color 255,255,255
	Rect 25,5,15,7
	RotateImage Acento,-30




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;
;;;;;;	MENU
;;;;;;



SetBuffer BackBuffer()
Cls
Repeat
	ControlaFPS_m()
	Cls
	DrawImage fondo_p,0,0
	

	;Molino 150x150
	If  (MouseX()>390) And (MouseX()<540) And  (MouseY()>404) And (MouseY()<555) Then
		;--------------------------------------
		DrawImage fuente_m,265,454,14 ;o
		DrawImage fuente_m,315,454,15 ;p
		DrawImage fuente_m,365,454,2 ;c
		DrawImage fuente_m,415,454,8 ;i
		DrawImage fuente_m,465,454,14 ;o
		DrawImage fuente_m,515,454,13 ;n
		DrawImage fuente_m,565,454,4 ;e
		DrawImage fuente_m,615,454,18 ;s
		;--------------------------------------
		If MouseHit(1)
			Transicion_out(fondo_p)
			opciones()
			Transicion_in(fondo_p)
		EndIf

	End If
	
	;Puente1 150x150
	If  (MouseX()>317) And (MouseX()<467) And  (MouseY()>245) And (MouseY()<395) Then
	;--------------------------------------
DrawImage fuente_m,192,295,2 ;c
DrawImage fuente_m,242,295,17 ;r
	DrawImage Acento,292,295
DrawImage fuente_m,292,295,4 ;e
DrawImage fuente_m,342,295,3 ;d
DrawImage fuente_m,392,295,8 ;i
DrawImage fuente_m,442,295,19 ;t
DrawImage fuente_m,492,295,14 ;o
DrawImage fuente_m,542,295,18 ;s
;--------------------------------------
		If MouseHit(1) Then 
			Creditos()
			Goto INICIO
		End If
	End If
	
	
	;Puente2 150x150
	If  (MouseX()>548) And (MouseX()<698) And  (MouseY()>270) And (MouseY()<420) Then
		;--------------------------------------
		DrawImage fuente_m,425,329,7 ;h
		DrawImage fuente_m,465,329,8 ;i
		DrawImage fuente_m,505,329,18 ;s
		DrawImage fuente_m,545,329,19 ;t
		DrawImage fuente_m,585,329,14 ;o
		DrawImage fuente_m,625,329,17 ;r
		DrawImage fuente_m,665,329,8 ;i
		DrawImage fuente_m,705,329,0 ;a
		DrawImage fuente_m,745,329,11 ;l
		;--------------------------------------
		
		If MouseHit(1) Then 
			Estadisticas()
		End If
	End If

	;Ficha 150x150
	If  (MouseX()>97) And (MouseX()<247) And  (MouseY()>329) And (MouseY()<479) Then

		;--------------------------------------
		DrawImage fuente_m,22,379,11 ;l
		DrawImage fuente_m,72,379,14 ;o
		DrawImage fuente_m,122,379,6 ;g
		DrawImage fuente_m,172,379,17 ;r
		DrawImage fuente_m,222,379,14 ;o
		DrawImage fuente_m,272,379,18 ;s
		;--------------------------------------
		If MouseHit(1)
			Transicion_out(fondo_p)
			logros()
			Transicion_in(fondo_p)
		EndIf

	End If

	;Rana 180x180
	If  (MouseX()>563) And (MouseX()<713) And  (MouseY()>73) And (MouseY()<223) Then

		;--------------------------------------
		DrawImage fuente_m,530,123,9 ;j
		DrawImage fuente_m,580,123,20 ;u
		DrawImage fuente_m,630,123,6 ;g
		DrawImage fuente_m,680,123,0 ;a
		DrawImage fuente_m,730,123,17 ;r
		;--------------------------------------
		If MouseHit(1)
			Transicion_out(fondo_p)
			Jugar()
			Goto INICIO
		EndIf
	End If
	
	If  (MouseX()>30) And (MouseX()<210) And  (MouseY()>490) And (MouseY()<560) Then
		If MouseHit(1) Then End
	End If
	FlushMouse()
	FlushKeys()
	

	Flip
	
	
Until KeyHit(1)

;----------------------------------------------------------------------------------------------
;LOGROS

;						Deben completarse en una partidas contra un oponente
;
;	1-Tirada Maestra: consigue más de 6 ranas en una tirada	-----------------> 
;	2-Juega 10 desafíos  ---------------------------------------------------->
;	2-El rey del molino; 5 o más molinos en una tirada	---------------------> 
;	3-Puente de oro; 5 o más puentes en una tirada	-------------------------> 
;	4-Calderilla: Consigue más de 6 agujeros simples en una tirada  --------->

;	5-Gran maestro	vence 5 veces un oponente en nivel 5---------------------> 
;	6-Aficionado: termina 10 partidas ------------------------>  
;	7-Tirada en blanco: hacer una tirada sin puntuar ------------------------>  
;	8-Supera los 2500 puntos en una partida  -------------------------------> 


;		1-Rana de oro	-------------------> consigue más de 5 ranas en una tirada	
;		2-Fichas de fuego   ---------------> juega 10 desafíos.		
;		3-Puentes de cristal  -------------> consigue más de 5 puentes en una tirada	
;		4-Molinillo loco   ----------------> consigue más de 5 molinillos en una tirada
;		5-Escenario Luna   ----------------> consigue más de 1400 puntos en una tirada.										
;		6-Fichas locas    -----------------> consigue 5 victorias en el nivel 1		
;		7-Caja de gigante  ----------------> consigue 5 victorias en el nivel 2					
;		8- 	-------> consigue 5 victorias en el nivel 3
				

; Pantalla donde se ven los logros conseguidos
Function logros()
	


	fondo = LoadImage ("Imagenes/fondo.jpg")
	logros_img = LoadAnimImage ("Imagenes/logros.jpg",150,150,0,8)
	MaskImage logros_img,255,0,0

	SetBuffer ImageBuffer(fondo)
	
	
	;--------------------------------------
	DrawImage fuente_m,250,50,11 ;l
	DrawImage fuente_m,300,50,14 ;o
	DrawImage fuente_m,350,50,6 ;g
	DrawImage fuente_m,400,50,17 ;r
	DrawImage fuente_m,450,50,14 ;o
	DrawImage fuente_m,500,50,18 ;s
	;--------------------------------------
	;marcos
	Color 255,255,255
	Rect 55,120,160,160
	Rect 235,120,160,160
	Rect 415,120,160,160
	Rect 595,120,160,160
	
	Rect 55,360,160,160
	Rect 235,360,160,160
	Rect 415,360,160,160
	Rect 595,360,160,160
	
	;logros
	For k = 1 To 4
		If Logros_desbloqueados(k) = True Then
			DrawImage logros_img,k*180-120,125,k-1
		Else
			DrawImage bloq_img,k*180-120,125
		End If
	Next
	For k = 5 To 8
		If Logros_desbloqueados(k) = True Then
			DrawImage logros_img,(k-4)*180-120,365,k-1
		Else
			DrawImage bloq_img,(k-4)*180-120,365
		End If
	Next

	;--------------------------------------
	DrawImage fuente_m_p,316,540,21 ;v
	DrawImage fuente_m_p,351,540,14 ;o
	DrawImage fuente_m_p,386,540,11 ;l
	DrawImage fuente_m_p,421,540,21 ;v
	DrawImage fuente_m_p,456,540,4 ;e
	DrawImage fuente_m_p,491,540,17 ;r
	;--------------------------------------
	Color 255,255,255
	Rect 150,290,500,50
	Color 0,0,0
	
	
	SetBuffer BackBuffer()
	Transicion_in(fondo)
	
	Repeat
		DrawImage fondo,0,0
		
		If (MouseX()>60) And (MouseX()<210) And (MouseY()>125) And (MouseY()<275)
			Text 160,300,"LOGRO #1:"
			Text 160,320,"Consigue más de 6 ranas en una tirada durante una desafío."
		Else If (MouseX()>240) And (MouseX()<390) And (MouseY()>125) And (MouseY()<275)
			Text 160,300,"LOGRO #2:"
			Text 160,320,"Juega 10 desafíos."

		Else If (MouseX()>420) And (MouseX()<570) And (MouseY()>125) And (MouseY()<275)
			Text 160,300,"LOGRO #3:"
			Text 160,320,"Consigue más de 6 puentes en una tirada durante un desafío"

		Else If (MouseX()>600) And (MouseX()<750) And (MouseY()>125) And (MouseY()<275)
			Text 160,300,"LOGRO #4:"
			Text 160,320,"Consigue más de 6 molinillos en una tirada durante un desafío"

		EndIf
		
		If (MouseX()>60) And (MouseX()<210) And (MouseY()>365) And (MouseY()<515)
			Text 160,300,"LOGRO #5:"
			Text 160,320,"Consigue más de 1500 puntos en un desafío"

		Else If (MouseX()>240) And (MouseX()<390) And (MouseY()>365) And (MouseY()<515)
			Text 160,300,"LOGRO #6:"
			Text 160,320,"Sal victorioso en 5 desafíos de nivel 1"

		Else If (MouseX()>420) And (MouseX()<570) And (MouseY()>365) And (MouseY()<515)
			Text 160,300,"LOGRO #7:"
			Text 160,320,"Sal victorioso en 5 desafíos de nivel 2"

		Else If (MouseX()>600) And (MouseX()<750) And (MouseY()>365) And (MouseY()<515)
			Text 160,300,"LOGRO #8:"
			Text 160,320,"Sal victorioso en 5 desafíos de nivel 3"

		EndIf


		
		
		If MouseHit(1)
			If (MouseX()>316) And (MouseX() < 514) And (MouseY() >540) And (MouseY()<575) Then
				Exit	
			End If
		End If
		Flip
	Until KeyHit(1)
	
	
	Transicion_out(fondo)


	
End Function

;----------------------------------------------------------------------------------------------
;LOGROS
;
;
;	Entrenamiento: se puede quitar el nerviosismo.
;	Jugar contra otro jugador.
;	Jugar contra un oponente: No se puede quitar el nerviosismo
;				Al vencer a un contrincante se desbloquean más.


;----------------------------------------------------------------------------------------------
;OPCIONES

Function opciones()
	
	
	fondo = LoadImage ("Imagenes/fondo.jpg")
	
	fichat = LoadAnimImage("Imagenes/fichasT.jpg",80,80,0,3)
	ranat = LoadAnimImage("Imagenes/rana.jpg",80,80,0,3)
	molinot =  LoadAnimImage("Imagenes/molinot.jpg",80,80,0,3)
	puentet =  LoadAnimImage("Imagenes/puentet.jpg",80,80,0,2)
	escenarios_i = LoadAnimImage("Imagenes/escenarios.jpg",320,240,0,5)
	MaskImage escenarios_i,255,255,255
	ScaleImage  escenarios_i,0.3,0.3
	transicion_in(fondo)

	
	
	der = False
	izq = True
	nf_d% = Num_Fichas/10
	nf_u% = Num_Fichas-nf_d*10
	
	nr_d% = Num_rondas/10
	nr_u% = Num_rondas-nr_d*10

	;Fondo opciones generales
	
	
	
	
	SetBuffer ImageBuffer(fondo)
		;--------------------------------------
		DrawImage fuente_m,200,30,14 ;o
		DrawImage fuente_m,250,30,15 ;p
		DrawImage fuente_m,300,30,2 ;c
		DrawImage fuente_m,350,30,8 ;i
		DrawImage fuente_m,400,30,14 ;o
		DrawImage fuente_m,450,30,13 ;n
		DrawImage fuente_m,500,30,4 ;e
		DrawImage fuente_m,550,30,18 ;s
		;--------------------------------------
		
		;--------------------------------------
		DrawImage fuente_m_p,316,540,21 ;v
		DrawImage fuente_m_p,351,540,14 ;o
		DrawImage fuente_m_p,386,540,11 ;l
		DrawImage fuente_m_p,421,540,21 ;v
		DrawImage fuente_m_p,456,540,4 ;e
		DrawImage fuente_m_p,491,540,17 ;r
		;--------------------------------------
	
		
		Color 0,0,0
		Rect 45,85,335,70
		Rect 420,85,335,70
		Color 191,93,1
		Rect 50,90,325,60
		Rect 425,90,325,60

	SetBuffer BackBuffer()
	
	Color 105,54,2
	
	Repeat

		Cls
		ControlaFPS_m()
		DrawImage fondo,0,0
		
		If izq = True Then
		;------------
			If MouseHit(1) Then 
				If (MouseX()>50) And (MouseX() < 375) And (MouseY() >90) And (MouseY()<150) Then
					If izq = False Then 
						der = False
						izq = True
					End If
				End If
		
				If (MouseX()>425) And (MouseX() < 750) And (MouseY() >90) And (MouseY()<150) Then	
					If der = False Then 
						izq = False
						der = True
					End If
				End If
			
				;sonido
					If (MouseX()>550) And (MouseX() < 655) And (MouseY() >200) And (MouseY()<235) Then
					If sonido = True Then
						sonido = False
					Else
						sonido = True
					End If
				End If
				
				;minicamera
				If (MouseX()>550) And (MouseX() < 655) And (MouseY() >270) And (MouseY()<305) Then
					If muestra_mini= True Then
						muestra_mini = False
					Else
						muestra_mini = True
					End If
				End If
			
				;fichas
				If (MouseX()>550) And (MouseX() < 655) And (MouseY() >340) And (MouseY()<375) Then
					Num_Fichas = Num_Fichas + 2
					If Num_Fichas > 10 Then Num_Fichas = 2
					nf_d% = Num_Fichas/10
					nf_u% = Num_Fichas-nf_d*10
				End If
			
				;Rondas
				If (MouseX()>550) And (MouseX() < 655) And (MouseY() >410) And (MouseY()<445) Then
					Num_rondas = Num_rondas+ 2
					If Num_rondas > 14 Then Num_rondas = 2
					nr_d% = num_rondas/10
					nr_u% = num_rondas-nr_d*10
				End If
				
				;Escenario
				If (MouseX()>535) And (MouseX() < 631) And (MouseY() >460) And (MouseY()<532) Then
					escenario_actual = escenario_actual +1
					
					If escenario_actual > NivelMax Then 
						If (Logros_desbloqueados(5) = True) And escenario_actual < 4 Then
							escenario_actual = 4
						Else
							escenario_actual = 0
						End If
						
					End If
				End If

				
				If (MouseX()>316) And (MouseX() < 514) And (MouseY() >540) And (MouseY()<575) Then
					Guarda_opc()
					Exit
				End If
				
			End If
		
		

			Rect 50,90,325,60
			DrawImage fondo_g,0,200
				
			;--------------------------------------
			DrawImage fuente_m,55,95,6 ;g
			DrawImage fuente_m,100,95,4 ;e
			DrawImage fuente_m,145,95,13 ;n
			DrawImage fuente_m,190,95,4 ;e
			DrawImage fuente_m,235,95,17 ;r
			DrawImage fuente_m,280,95,0 ;a
			DrawImage fuente_m,325,95,11 ;l
			;--------------------------------------
			
			;--------------------------------------
			DrawImage fuente_m,430,95,4 ;e
			DrawImage fuente_m,475,95,23 ;x
			DrawImage fuente_m,520,95,19 ;t
			DrawImage fuente_m,565,95,17 ;r
			DrawImage fuente_m,610,95,0 ;a
			DrawImage fuente_m,655,95,18 ;s
			;--------------------------------------
			
			If sonido = True Then
				;--------------------------------------
				DrawImage fuente_m_p,550,200,14 ;o
				DrawImage fuente_m_p,585,200,13 ;n
				;--------------------------------------
			Else 
				;--------------------------------------
				DrawImage fuente_m_p,550,200,14 ;o
				DrawImage fuente_m_p,585,200,5 ;f
				DrawImage fuente_m_p,620,200,5 ;f
				;--------------------------------------
			End If
		
			If muestra_mini = True Then
				;--------------------------------------
				DrawImage fuente_m_p,550,270,14 ;o
				DrawImage fuente_m_p,585,270,13 ;n
				;--------------------------------------
			Else 
				;--------------------------------------
				DrawImage fuente_m_p,550,270,14 ;o
				DrawImage fuente_m_p,585,270,5 ;f
				DrawImage fuente_m_p,620,270,5 ;f
				;--------------------------------------
			End If
			
			DrawImage fuente_m_p,550,340,26+nf_d%
			DrawImage fuente_m_p,585,340,26+nf_u%
			
			DrawImage fuente_m_p,550,410,26+nr_d%
			DrawImage fuente_m_p,585,410,26+nr_u%
			
			DrawImage escenarios_i,535,460,escenario_actual
			
	
		;---------					
		Else
		;---------
			Rect 425,90,325,60
			DrawImage fondo_l,0,200
			
			; Tipos de rana
			If (Logros_desbloqueados(1) = True) Or (Logros_desbloqueados(7) = True) Then 
				DrawImage boton,690,215,0
				DrawImage boton,540,215,2
			Else 
				DrawImage boton,690,215,1
				DrawImage boton,540,215,3
			End If
			
			;Tipos de puente
			If Logros_desbloqueados(4) = True Then 
				DrawImage boton,690,305,0
				DrawImage boton,540,305,2
			Else 
				DrawImage boton,690,305,1
				DrawImage boton,540,305,3
			End If

			; Tipos de molino
			If (Logros_desbloqueados(3) = True) Or Logros_desbloqueados(8) = True Then
				DrawImage boton,690,395,0
				DrawImage boton,540,395,2
			Else
				DrawImage boton,690,395,1
				DrawImage boton,540,395,3

			End If
			
			; Tipos de fichas
			If (Logros_desbloqueados(2)= True) Or (Logros_desbloqueados(6) = True) Then 
				DrawImage boton,690,485,0
				DrawImage boton,540,485,2
			Else
				DrawImage boton,690,485,1
				DrawImage boton,540,485,3
			End If
			
			
			
			
			If MouseHit(1) Then 
				;GENERAL
				If (MouseX()>50) And (MouseX() < 375) And (MouseY() >90) And (MouseY()<150) Then
					If izq = False Then 
						der = False
						izq = True
					End If
				End If
				;LOGROS
				If (MouseX()>425) And (MouseX() < 750) And (MouseY() >90) And (MouseY()<150) Then	
					If der = False Then 
						izq = False
						der = True
					End If
				End If
				
				; Tipo Rana
				If (MouseX()>690) And (MouseX() < 740) And (MouseY() >215) And (MouseY()<265) Then
					If (Logros_desbloqueados(1) = True) And (Logros_desbloqueados(7) = True) Then
						If TipoRana = 0 Then 
							TipoRana = 1
						Else If TipoRana = 1  Then 
							TipoRana = 2
						Else If TipoRana = 2 Then 
							TipoRana = 0
						End If
					End If
					If (Logros_desbloqueados(1) = False) And (Logros_desbloqueados(7) = True) Then
						If TipoRana = 0  Then 
							TipoRana = 2
						Else If TipoRana = 2 Then 
							TipoRana = 0
						End If
					End If
					If (Logros_desbloqueados(1) = True) And (Logros_desbloqueados(7) = False) Then
						If TipoRana = 0 Then
							TipoRana = 1
						Else If TipoRana = 1 Then
							TipoRana = 0
						End If
					End If
				End If
				
				If (MouseX()>540) And (MouseX() < 590) And (MouseY() >215) And (MouseY()<265) Then
					If (Logros_desbloqueados(1) = True) And (Logros_desbloqueados(7) = True) Then
						If TipoRana = 0 Then 
						    TipoRana = 2
						Else If TipoRana = 2 Then 
							TipoRana = 1
						Else If TipoRana = 1 Then
							TipoRana = 0
						End If
					End If
					If (Logros_desbloqueados(1) = False) And (Logros_desbloqueados(7) = True) Then
						If TipoRana = 0 Then
							TipoRana= 2
						Else If TipoRana = 2 Then
							TipoRana= 0
						End If
					End If
					If (Logros_desbloqueados(1) = True) And (Logros_desbloqueados(7) = False) Then
						If TipoRana= 0 Then
							TipoRana = 1
						Else If TipoRana = 1 Then
							TipoRana= 0
						End If
					End If
				End If
   
			
				;Tipos de puente
				If (MouseX()>690) And (MouseX() < 740) And (MouseY() >305) And (MouseY()<355) Then
				    If (TipoPuente = 0) And (Logros_desbloqueados(4) = True) Then
						TipoPuente = 1
					Else If (TipoPuente = 1) And (Logros_desbloqueados(4) = True) Then
						TipoPuente = 0
					End If	
				End If
				If (MouseX()>540) And (MouseX() <590) And (MouseY() >305) And (MouseY()<355) Then
				    If (TipoPuente = 0) And (Logros_desbloqueados(4) = True) Then
						TipoPuente = 1
					Else If (TipoPuente = 1) And (Logros_desbloqueados(4) = True) Then
						TipoPuente = 0
					End If
				End If

		
				;Tipos de molino
				If (MouseX()>690) And (MouseX() < 740) And (MouseY() >395) And (MouseY()<445) Then
				    If (Logros_desbloqueados(3) = True) And (Logros_desbloqueados(8) = True) Then
						If TipoMolino = 0 Then 
							TipoMolino = 1
						Else If TipoMolino = 1  Then 
							TipoMolino= 2
						Else If TipoMolino = 2 Then 
							TipoMolino = 0
						End If
					End If
					If (Logros_desbloqueados(3) = False) And (Logros_desbloqueados(8) = True) Then
						If TipoMolino = 0  Then 
							TipoMolino = 2
						Else If TipoMolino = 2 Then 
							TipoMolino = 0
						End If
					End If
					If (Logros_desbloqueados(3) = True) And (Logros_desbloqueados(8) = False) Then
						If TipoMolino = 0 Then
							TipoMolino = 1
						Else If TipoMolino = 1 Then
							TipoMolino = 0
						End If
					End If
	
				End If
				If (MouseX()>540) And (MouseX() < 590) And (MouseY() >395) And (MouseY()<445) Then
				    If (Logros_desbloqueados(3) = True) And (Logros_desbloqueados(8) = True) Then
						If TipoMolino = 0 Then 
							TipoMolino = 2
						Else If TipoMolino = 2  Then 
							TipoMolino= 1
						Else If TipoMolino = 1 Then 
							TipoMolino = 0
						End If
					End If
					If (Logros_desbloqueados(3) = False) And (Logros_desbloqueados(8) = True) Then
						If TipoMolino = 0  Then 
							TipoMolino = 2
						Else If TipoMolino = 2 Then 
							TipoMolino = 0
						End If
					End If
					If (Logros_desbloqueados(3) = True) And (Logros_desbloqueados(8) = False) Then
						If TipoMolino = 0 Then
							TipoMolino = 1
						Else If TipoMolino = 1 Then
							TipoMolino = 0
						End If
					End If

				End If

			
		    	;Tipos de fichas
				If (MouseX()>690) And (MouseX() < 740) And (MouseY() >485) And (MouseY()<535) Then
					If (Logros_desbloqueados(2) = True) And (Logros_desbloqueados(6) = True) Then
						If TipoFichas = 0 Then 
							TipoFichas = 1
						Else If TipoFichas = 1  Then 
							TipoFichas = 2
						Else If TipoFichas = 2 Then 
							TipoFichas = 0
						End If
					End If
					If (Logros_desbloqueados(2) = True) And (Logros_desbloqueados(6) = False) Then
						If TipoFichas = 0  Then 
							TipoFichas = 2
						Else If TipoFichas = 2 Then 
							TipoFichas = 0
						End If
					End If
					If (Logros_desbloqueados(2) = False) And (Logros_desbloqueados(6) = True) Then
						If TipoFichas = 0 Then
							TipoFichas = 1
						Else If TipoFichas = 1 Then
							TipoFichas = 0
						End If
					End If
				End If
				
				If (MouseX()>540) And (MouseX() < 590) And (MouseY() >485) And (MouseY()<535) Then
				    If (Logros_desbloqueados(2) = True) And (Logros_desbloqueados(6) = True) Then
						If TipoFichas = 0 Then 
						    TipoFichas = 2
						Else If TipoFichas = 2 Then 
							TipoFichas = 1
						Else If TipoFichas = 1 Then
							TipoFichas = 0
						End If
					End If
					If (Logros_desbloqueados(2) = True) And (Logros_desbloqueados(6) = False) Then
						If TipoFichas = 0 Then
							TipoFichas = 2
						Else If TipoFichas = 2 Then
							TipoFichas = 0
						End If
					End If
					If (Logros_desbloqueados(2) = False) And (Logros_desbloqueados(6) = True) Then
						If TipoFichas = 0 Then
							TipoFichas = 1
						Else If TipoFichas = 1 Then
							TipoFichas = 0
						End If
					End If
				End If
			
				
				; Salir		
				If (MouseX()>316) And (MouseX() < 514) And (MouseY() >540) And (MouseY()<575) Then
					Guarda_opc()
					Exit
				End If
		 End If
		
		DrawImage ranat,600,200,TipoRana
		DrawImage puentet,600,290,TipoPuente
		DrawImage molinot,600,380,TipoMolino
		DrawImage fichat,600,470,TipoFichas
		
		
		
				
		;--------------------------------------
		DrawImage fuente_m,55,95,6 ;g
		DrawImage fuente_m,100,95,4 ;e
		DrawImage fuente_m,145,95,13 ;n
		DrawImage fuente_m,190,95,4 ;e
		DrawImage fuente_m,235,95,17 ;r
		DrawImage fuente_m,280,95,0 ;a
		DrawImage fuente_m,325,95,11 ;l
		;--------------------------------------
		
		;--------------------------------------
		DrawImage fuente_m,430,95,4 ;e
		DrawImage fuente_m,475,95,23 ;x
		DrawImage fuente_m,520,95,19 ;t
		DrawImage fuente_m,565,95,17 ;r
		DrawImage fuente_m,610,95,0 ;a
		DrawImage fuente_m,655,95,18 ;s
		;--------------------------------------

		End If
		
		Text 0,0,MouseX() 
		Text 0,20,MouseY() 
		
		Flip
	Until KeyHit(1)
	
	Guarda_opc()
	Transicion_out(fondo)
	
	FreeImage escenarios_i
	FreeImage fichat 
	FreeImage ranat
	FreeImage molinot
	FreeImage puentet

	

End Function

;----------------------------------------------------------------------------------------------
;JUGAR

Function Jugar()

	
	fondo = LoadImage ("Imagenes/fondo.jpg")
	
	
	
	
	Transicion_in(fondo)
	SetBuffer ImageBuffer(fondo)
		
		Color 0,0,0
		Rect 50,100,400,90
		Rect 50,250,400,90
		Rect 50,410,400,90
		
		Rect 480,100,280,400

		
		Color 255,0,0
		Rect 60,110,380,70
		Rect 60,260,380,70
		Rect 60,420,380,70
		
		Color 207,111,25
		Rect 490,110,260,380
		
		;--------------------------------------
		DrawImage fuente_m,65,120,15 ;p
		DrawImage fuente_m,110,120,17 ;r
			DrawImage Acento,155,120
		DrawImage fuente_m,155,120,0 ;a
		DrawImage fuente_m,200,120,2 ;c
		DrawImage fuente_m,245,120,19 ;t
		DrawImage fuente_m,290,120,8 ;i
		DrawImage fuente_m,335,120,2 ;c
		DrawImage fuente_m,380,120,0 ;a
		;--------------------------------------
		
		;--------------------------------------
		DrawImage fuente_m_p,68,275,12 ;M
		DrawImage fuente_m_p,98,275,20 ;u
		DrawImage fuente_m_p,128,275,11 ;l
		DrawImage fuente_m_p,158,275,19 ;t
		DrawImage fuente_m_p,188,275,8 ;i
		DrawImage fuente_m_p,218,275,9 ;j
		DrawImage fuente_m_p,248,275,20 ;u
		DrawImage fuente_m_p,278,275,6 ;g
		DrawImage fuente_m_p,308,275,0 ;a
		DrawImage fuente_m_p,338,275,3 ;d
		DrawImage fuente_m_p,368,275,14 ;o
		DrawImage fuente_m_p,398,275,17 ;r
		;--------------------------------------
		
		;--------------------------------------
		DrawImage fuente_m,65,430,3 ;d
		DrawImage fuente_m,110,430,4 ;e
		DrawImage fuente_m,155,430,18 ;s
		DrawImage fuente_m,200,430,0 ;a
		DrawImage fuente_m,245,430,5 ;f
			DrawImage Acento,290,430
		DrawImage fuente_m,290,430,8 ;i
		DrawImage fuente_m,335,430,14 ;o
		DrawImage fuente_m,380,430,18 ;s
		;--------------------------------------
		
		;--------------------------------------
		DrawImage fuente_m_p,316,540,21 ;v
		DrawImage fuente_m_p,351,540,14 ;o
		DrawImage fuente_m_p,386,540,11 ;l
		DrawImage fuente_m_p,421,540,21 ;v
		DrawImage fuente_m_p,456,540,4 ;e
		DrawImage fuente_m_p,491,540,17 ;r
		;--------------------------------------
		
	info_prac = CreateImage(260,380)
	SetBuffer ImageBuffer(info_prac)
	Color 255,255,255
	;			"--------------------------------"
	Text 2,5,	"Juega una partida en solitario"
	Text 2,25,	"para praticar tu juego. Puedes" 
	Text 2,45,	"escoger el escenario y cambiar" 
	Text 2,65,	"la configuración del juego en"
	Text 2,85,	"las opciones (molinillo)  del "
	Text 2,105,	"menú principal."
	
	Text 2,135,	"Las puntuaciones en estas"
	Text 2,155, "partidas no se tienen en cuenta" 
	Text 2,175,	"para desbloquear LOGROS."
	
	
	info_multi = CreateImage(260,380)
	SetBuffer ImageBuffer(info_multi)
	Color 255,255,255
	;			"--------------------------------"
	Text 2,5,	"Juega una partida contra otras"
	Text 2,25,	"personas hasta un máximo de" 
	Text 2,45,	"cuatro jugadores." 
	Text 2,65,	"Modifica las características del"
	Text 2,85,	"juego en las opciones (molinillo)"
	Text 2,105,	"del menú principal."
	
	Text 2,135,	"Las puntuaciones en estas"
	Text 2,155, "partidas no se tienen en cuenta" 
	Text 2,175,	"para desbloquear LOGROS."
	
	info_desa = CreateImage(260,380)
	SetBuffer ImageBuffer(info_desa)
	Color 255,255,255
	;			"--------------------------------"
	Text 2,5,	"Desafía a diferentes oponentes."
	Text 2,25,	"Vence a los más débiles para " 
	Text 2,45,	"poder enfrentarte a jugadores" 
	Text 2,65,	"más cualificados."
	Text 2,85,	"En el transcurso de estas"
	Text 2,105,	"partidas puedes desbloquear"
	Text 2,125,	"LOGROS y conseguir algunos "
	Text 2,145,	"extras."

	Text 2,165,	"En estas partidas constan de 8"
	Text 2,185,	"rondas, y de 10 fichas por ronda."

	



	SetBuffer BackBuffer()
	
	Cls
	Repeat
		
		DrawImage fondo,0,0
		;Práctica
		If (MouseX()>60) And (MouseX() < 440) And (MouseY() >110) And (MouseY()<180) Then
			DrawImage info_prac,490,110
			If MouseHit(1) Then
				Num_Jug = 1
				NivelSel = -1 ; Para que no cuenten los logros
				TipoPractica = True	; Para que no saque la puntuación final
				Transicion_out(fondo)
				Comienza_Juego()
				Exit
			End If
		End If
		
		;Multijugador
		If (MouseX()>60) And (MouseX() < 440) And (MouseY() >260) And (MouseY()<330) Then
			DrawImage info_multi,490,110
			If MouseHit(1) Then
				Transicion_out(fondo)
				Multijugador()
				Exit
			End If

		End If

		;Desafío
		If (MouseX()>60) And (MouseX() < 440) And (MouseY() >420) And (MouseY()<490) Then
			DrawImage info_desa,490,110
			If MouseHit(1) Then
				Transicion_out(fondo)
				Desafio()
				Exit
			End If
		End If

		If (MouseX()>316) And (MouseX() < 514) And (MouseY() >540) And (MouseY()<575) Then
			Text 492,115, "Volver al menú principal"
			If MouseHit(1) Then
				Transicion_out(fondo)
				Exit
			EndIf 
		End If
		
		FlushMouse()

		
		Flip
	Until KeyHit(1)
	
	

End Function

Function Multijugador()
	fondo = LoadImage ("Imagenes/fondo.jpg")
	
	

	
	Num_Jug = 2
	
	SetBuffer ImageBuffer(fondo)
		
		Color 204,113,6
		
		Rect 150,300,100,100
		Rect 350,300,100,100
		Rect 550,300,100,100
		
		;--------------------------------------
		DrawImage fuente_m,50,80,9 ;j
		DrawImage fuente_m,95,80,20 ;u
		DrawImage fuente_m,140,80,6 ;g
		DrawImage fuente_m,185,80,0 ;a
		DrawImage fuente_m,230,80,3 ;d
		DrawImage fuente_m,275,80,14 ;o
		DrawImage fuente_m,320,80,17 ;r
		DrawImage fuente_m,365,80,4 ;e
		DrawImage fuente_m,410,80,18 ;s
		;--------------------------------------
	
		;--------------------------------------
		DrawImage fuente_m,200,520,4 ;e
		DrawImage fuente_m,245,520,12 ;m
		DrawImage fuente_m,290,520,4 ;e
		DrawImage fuente_m,335,520,15 ;p
		DrawImage fuente_m,380,520,4 ;e
		DrawImage fuente_m,425,520,25 ;z
		DrawImage fuente_m,470,520,0 ;a
		DrawImage fuente_m,515,520,17 ;r
		;--------------------------------------
	
	SetBuffer BackBuffer()
	Transicion_in(fondo)
	
	Repeat 
		DrawImage fondo,0,0
		
		Select Num_Jug
			Case 2
				Color 255,0,0
				Rect 155,305,90,90
			Case 3
				Color 255,0,0
				Rect 355,305,90,90
			Case 4
				Color 255,0,0
				Rect 555,305,90,90
		End Select
		
		
		DrawImage fuente_m,175,325,28
		DrawImage fuente_m,375,325,29
		DrawImage fuente_m,575,325,30
		
		If MouseHit(1)
			If (MouseX()> 150) And (MouseX()< 250) And (MouseY()> 300) And (MouseY()< 400) Then
				Num_Jug = 2
			End If 
		
			If (MouseX()> 350) And (MouseX()< 450) And (MouseY()> 300) And (MouseY()< 400) Then
				Num_Jug = 3
			End If
			
			If (MouseX()> 550) And (MouseX()< 650) And (MouseY()> 300) And (MouseY()< 400) Then
				Num_Jug = 4
			End If
			
			If (MouseX()> 200) And (MouseX()< 560) And (MouseY()> 520) And (MouseY()< 570) Then			
				Transicion_out(fondo)
				NivelSel = -1 ; Para que no cuenten los logros
				Comienza_Juego()
				Exit	
			
			End If
			
		
		End If
	Flip
	Until KeyHit(1)
	
	

End Function


Function Desafio()
	fondo = LoadImage ("Imagenes/fondo.jpg")
	niveles_i = LoadAnimImage("Imagenes/niveles.jpg",100,100,0,4)
	escenarios_i = LoadAnimImage("Imagenes/escenarios.jpg",320,240,0,5)
	MaskImage niveles_i,214,125,125
	cod = CreateImage(100,100)
	
	SetBuffer ImageBuffer(cod)
	For i = 0 To 5
		For j = 0 To 5
			C = Rnd(255)
			Color C,C,C
			Rect i*20,j*20,20,20
		Next
	Next

	
	SetBuffer ImageBuffer(fondo)
		
		Color 0,0,0
		Rect 240,80,320,240
		
		;--------------------------------------
		DrawImage fuente_m,200,520,4 ;e
		DrawImage fuente_m,245,520,12 ;m
		DrawImage fuente_m,290,520,4 ;e
		DrawImage fuente_m,335,520,15 ;p
		DrawImage fuente_m,380,520,4 ;e
		DrawImage fuente_m,425,520,25 ;z
		DrawImage fuente_m,470,520,0 ;a
		DrawImage fuente_m,515,520,17 ;r
		;--------------------------------------
	
	SetBuffer BackBuffer()
	
	Transicion_in(fondo)
	NivelSel = 0
	
	Repeat 
	
		DrawImage fondo,0,0
		
		
		Color 255,255,255
		Rect 77+NivelSel*175,340,120,120
		
			
		DrawImage escenarios_i,240,80,NivelSel
		
	
		For i = 0 To NivelMax
			DrawImage niveles_i,87+i*175,350,i
		Next
		For i = NivelMax+1 To 3
			DrawImage cod,87+i*175,350
		Next
				
				
		If MouseHit(1)
			If (MouseX()> 87) And (MouseX()< 187) And (MouseY()> 350) And (MouseY()< 450) Then
				NivelSel = 0
			End If 
		
			If (MouseX()> 262) And (MouseX()< 362) And (MouseY()> 350) And (MouseY()< 450) Then
				If NivelMax > 0 Then NivelSel = 1
			End If
			
			If (MouseX()> 437) And (MouseX()< 537) And (MouseY()> 350) And (MouseY()< 450) Then
				If NivelMax > 1 Then NivelSel = 2
			End If
			
			If (MouseX()> 612) And (MouseX()< 712) And (MouseY()> 350) And (MouseY()< 450) Then
				If NivelMax > 2 Then NivelSel = 3
			End If
			If (MouseX()> 200) And (MouseX()< 560) And (MouseY()> 520) And (MouseY()< 570) Then			
				Transicion_out(fondo)
				escenario_actual = NivelSel
				Num_Fichas = 10
				Num_rondas = 8
				TipoRana = 0
				TipoMolino = 0
				TipoPuente = 0
				TipoFichas = 0
				num_jug = 2
				jugadores_tipo(1) = 0	; El humano siempre el jugador 1
				jugadores_tipo(2) = 1

				Comienza_Juego()
				Exit
					
			End If	
		End If
		Flip
		
	Until KeyHit(1)
	
	FreeImage fondo
	FreeImage niveles_i
	FreeImage escenarios_i


End Function 

;----------------------------------------------------------------------------------------------
;Ayuda

Function Ayuda()
	
	fondo = LoadImage ("Imagenes/fondo.jpg")
End Function


; Estadísticas

;Se inlcuyen en otro fichero
Include "estadisticas.bb"
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;----------------------------------------------------------------------------------------------
Function Transicion_out(fondo)
	x# =0.1
	Repeat
		ControlaFPS_m()
		Cls
		DrawImage fondo,x,0
		x =x + 40
		Flip
	
	Until (x>800)

End Function

Function Transicion_in(fondo)
	x0# =-800
	Repeat
		ControlaFPS_m()
		Cls
		DrawImage fondo,x0,0
		x0 =x0+40
		Flip
	
	Until (x0>0)

End Function

Function MuestraFPS_M()

	time1 = MilliSecs()
	fps_r# =1000/ (time2-time1)
	time2=time1
	Text 10,550,"FPS:"+fps_r#

End Function

;Establece el número de frames por segundo
Function ControlaFPS_m()
		.ESPERA
		If (MilliSecs() -time0) < mspf Then Goto ESPERA
		time0 = MilliSecs()
End Function