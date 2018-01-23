;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;
;;;;;;		Rutinas de guardado y carga de datos
;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;Orden de los datos en el fichero: (CADA DATO ES UN ENTERO)
	;Opciones
;		-Sonido			sonido
;		-Vista Aux		mini_camera
;		-Fichas			Num_fichas
;		-Rondas			Num_rondas
;		-Escenario		escenario_actual
;		-Rana
;		-Molinillo
;		-Puente
;		-Fichas
;
;		

;--------------------------------------------------------------------------
; OPCIONES

Function Guarda_opc ()
	fd = WriteFile("data/opc.ini")
		; Opciones
		WriteInt(fd,sonido)					;sonido (0 ó 1)
		WriteInt(fd,muestra_mini)			;mini_camera (0 ó 1)
		WriteInt(fd,num_fichas)				;num_fichas (2,4,6,8,10
		WriteInt(fd,num_rondas)				;num_rondas (,2,4,8,13,12)
		WriteInt(fd,escenario_actual)		;escenario actual (0,1,2,3,4,5,6,7,8)
		
	CloseFile(fd)
End Function

Function Carga_opc()
	fd = ReadFile("data/opc.ini")

	If fd <> 0 Then		
		sonido =ReadInt(fd)				;sonido (0 ó 1)
		muestra_mini=ReadInt(fd)			;mini_camera (0 ó 1)
		num_fichas=ReadInt(fd)			;num_fichas (2,4,6,8,10
		num_rondas=ReadInt(fd)			;num_rondas (2,4,8,13,12)
		escenario_actual=ReadInt(fd)	;escenario actual (0,1,2,3,4,5,6,7,8)
		CloseFile(fd)
	Else
		sonido = True
		muestra_mini = True
		num_fichas = 6
		num_rondas = 8
		escenario_actual = 0
	End If
		
		


End Function


;----------------------------------------------------------------------------
; LOGROS

Function Guarda_log ()
	fd = WriteFile("data/log.ini")
		
	
		; Logros
		For k = 1 To 8
			WriteInt(fd,Logros_desbloqueados(k))	;Rana_dorada
		Next
		;Estadísticas
		WriteInt(fd, desafios_0_jug)
		WriteInt(fd,desafios_0_gan)
		WriteInt(fd,desafios_1_jug)
		WriteInt(fd,desafios_1_gan)
		WriteInt(fd,desafios_2_jug)
		WriteInt(fd,desafios_2_gan)
		WriteInt(fd, desafios_3_jug)
		WriteInt(fd, desafios_3_gan)

		WriteInt(fd,ranas_tmp)
		WriteInt(fd, ranas_max)
		WriteInt(fd, molinos_tmp)
		WriteInt(fd, molinos_max)
		WriteInt(fd,puentes_tmp)
		WriteInt(fd,puentes_max)
		WriteInt(fd,puntos_tir_tmp)
		WriteInt(fd,puntos_tir_max)
		WriteInt(fd,puntos_par_tmp)
		WriteInt(fd,puntos_par_max)

	CloseFile(fd)
	
End Function

Function Carga_log ()

	fd = ReadFile("data/log.ini")
	If fd <> 0 Then	
	
    		; Logros
		For k = 1 To 8
			Logros_desbloqueados(k) = ReadInt(fd)	;Rana_dorada
		Next
		
		;Estadísticas
		desafios_0_jug=ReadInt(fd)
		desafios_0_gan=ReadInt(fd)
		desafios_1_jug=ReadInt(fd)
		desafios_1_gan=ReadInt(fd)
		desafios_2_jug=ReadInt(fd)
		desafios_2_gan=ReadInt(fd)
		desafios_3_jug=ReadInt(fd)
		desafios_3_gan=ReadInt(fd)

		ranas_tmp = ReadInt(fd)
		ranas_max = ReadInt(fd)
		molinos_tmp = ReadInt(fd)
		molinos_max = ReadInt(fd)
	    puentes_tmp = ReadInt(fd)
		puentes_max = ReadInt(fd)
		puntos_tir_tmp = ReadInt(fd)
		puntos_tir_max = ReadInt(fd)
		puntos_par_tmp = ReadInt(fd)
		puntos_par_max = ReadInt(fd)
		CloseFile(fd)
	Else
			; Logros
		For k = 1 To 8
			Logros_desbloqueados(k) = False	;Rana_dorada
		Next
		
		;Estadísticas
		desafios_0_jug=0
		desafios_0_gan=0
		desafios_1_jug=0
		desafios_1_gan=0
		desafios_2_jug=0
		desafios_2_gan=0
		desafios_3_jug=0
		desafios_3_gan=0

		ranas_tmp = 0
		ranas_max = 0
		molinos_tmp = 0
		molinos_max = 0
	    puentes_tmp = 0
		puentes_max = 0
		puntos_tir_tmp = 0
		puntos_tir_max = 0
		puntos_par_tmp = 0
		puntos_par_max = 0

	End If
	
End Function
