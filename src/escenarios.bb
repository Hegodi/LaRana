;******************************************************************************************
;*****
;*****				ESCENARIOS
;*****
;******************************************************************************************


; Lo cara en la variable global decorado (tipo Escenario).
; Esta ya viene limpia




; Escenario Rival 0
;-----------------------------------------------------------------------------
Function Escenario_0()

	decorado.Escenario = New Escenario
	
	decorado\suelo = CreateCube()
	decorado\suelo_t = LoadTexture("Texturas/ground1.jpg")
	ScaleEntity decorado\suelo,100,0.5,100
	PositionEntity decorado\suelo,0,-35,0
	EntityTexture decorado\suelo, decorado\suelo_t
	EntityType decorado\suelo,C_escenario
	
	decorado\obj1 = CreateCube()
	decorado\obj1_t = LoadTexture("Texturas/wall1.jpg")
	ScaleEntity decorado\obj1,80,50,0.5
	PositionEntity decorado\obj1,0,0,30
	EntityTexture decorado\obj1, decorado\obj1_t
	EntityType decorado\obj1,C_escenario
	

	
End Function

Function Borra_Escenario_0()
	FreeEntity decorado\suelo
	FreeEntity decorado\obj1
	FreeTexture decorado\suelo_t
	FreeTexture decorado\obj1_t
End Function




; Escenario Rival 1
;-----------------------------------------------------------------------------

Function Escenario_1()
	AmbientLight 100,100,100
	
	decorado\suelo = LoadTerrain("imagenes/level2.bmp")
	decorado\suelo_t =LoadTexture("Texturas/grass.jpg")
	ScaleTexture decorado\suelo_t,20,20
	ScaleEntity decorado\suelo,1.5,40,1.5
	PositionEntity decorado\suelo,-128,-35,-128
	EntityTexture decorado\suelo, decorado\suelo_t
	EntityType decorado\suelo,C_escenario
	TerrainShading decorado\suelo,eneable	
	
	decorado\cielo = CreateSphere()
	decorado\cielo_t = LoadTexture("Texturas/cielo.jpg")
	ScaleEntity decorado\cielo,500,500,500
	RotateEntity decorado\cielo,0,90,0
	FlipMesh (decorado\cielo)
	EntityTexture decorado\cielo, decorado\cielo_t
	
	
	decorado\obj1 = LoadSprite("Imagenes/arbol.bmp",4)
	ScaleSprite decorado\obj1,30,30
	PositionEntity decorado\obj1,20,5,40


	


End Function

Function Borra_Escenario_1()
	FreeEntity decorado\suelo
	FreeEntity decorado\cielo
	FreeTexture decorado\suelo_t
	FreeTexture decorado\cielo_t
	FreeEntity decorado\obj1
End Function




; Escenario Rival 3
;-----------------------------------------------------------------------------

Function Escenario_2()
	decorado\suelo = CreateCube()
	decorado\suelo_t = LoadTexture("Texturas/baldosa.jpg")
	ScaleEntity decorado\suelo,100,0.5,100
	ScaleTexture decorado\suelo_t,0.08,0.08
	 
	PositionEntity decorado\suelo,0,-35,0
	EntityTexture decorado\suelo, decorado\suelo_t
	EntityType decorado\suelo,C_escenario
	
	decorado\obj1 = CreateCube()
	decorado\obj1_t = LoadTexture("Texturas/patio.jpg")
	;ScaleTexture decorado\obj1_t,.8,.8
	ScaleEntity decorado\obj1,80,40,0.5
	PositionEntity decorado\obj1,0,0,80
	RotateEntity decorado\obj1,10,0,0
	EntityTexture decorado\obj1, decorado\obj1_t
	EntityType decorado\obj1,C_escenario

End Function

Function Borra_Escenario_2()
	FreeEntity decorado\suelo
	FreeTexture decorado\suelo_t
	FreeTexture decorado\obj1_t
	FreeEntity decorado\obj1
End Function




; Escenario Rival 2
;-----------------------------------------------------------------------------

Function Escenario_3()
    ;LightColor light, 255,204,0
	;AmbientLight 255,01,50


	decorado\suelo = CreateCube()
	decorado\suelo_t = LoadTexture("Texturas/ground2.jpg")
	ScaleEntity decorado\suelo,100,0.5,100
	ScaleTexture decorado\suelo_t,0.3,100
	PositionEntity decorado\suelo,0,-35,0
	EntityTexture decorado\suelo, decorado\suelo_t
	EntityType decorado\suelo,C_escenario
	
	decorado\obj1 = CreateCube()
	decorado\obj1_t = LoadTexture("Texturas/paredcasa.jpg")
	ScaleTexture decorado\obj1_t,1,1
	ScaleEntity decorado\obj1,100,50,0.5
	PositionEntity decorado\obj1,0,15,100
	RotateEntity decorado\obj1,10,0,0
	EntityTexture decorado\obj1, decorado\obj1_t
	EntityType decorado\obj1,C_escenario

End Function

Function Borra_Escenario_3()
	FreeEntity decorado\suelo
	FreeTexture decorado\suelo_t
	FreeTexture decorado\obj1_t
	FreeEntity decorado\obj1
End Function



; Escenario Luna (LOGRO)
;-----------------------------------------------------------------------------

Function Escenario_4()
	
	escenario_actual = 6
	LightColor light,30,30,100
	AmbientLight 10,10,50
	CameraFogMode camera,1
	CameraFogRange camera,100,300
	
	
	decorado\suelo = CreatePlane()
	decorado\suelo_t =LoadTexture("Texturas/moon.jpg")
	ScaleTexture decorado\suelo_t,100,100
	;ScaleEntity decorado\suelo,1.
	PositionEntity decorado\suelo,0,-35,0
	EntityTexture decorado\suelo, decorado\suelo_t
	EntityType decorado\suelo,C_escenario

	decorado\cielo = CreateSphere()
	EntityColor decorado\cielo,0,0,5
	ScaleEntity decorado\cielo,200,200,200
	FlipMesh (decorado\cielo)

	decorado\cielo_t = LoadTexture("Texturas/moon.bmp",2+1)
	decorado\obj1 = CreateSprite()
	EntityTexture decorado\obj1, decorado\cielo_t
	PositionEntity decorado\obj1,10,10,0
	ScaleSprite decorado\obj1,5,5
	
	

End Function

Function Borra_Escenario_4()
	FreeEntity decorado\suelo
	FreeEntity decorado\cielo
	FreeTexture decorado\suelo_t
	FreeTexture decorado\cielo_t
	FreeEntity decorado\obj1
End Function