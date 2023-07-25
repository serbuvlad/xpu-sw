;-------------------------------------------------------------------------------------
;	name:
;		prim_initialize
;	info:
;		initial configurations for xpu accelerator.
;	requirements hardware:
;		none
;	requirements variables/parameters locations:
;		none
;	labels:
;	    none
;-------------------------------------------------------------------------------------

include "configurations.asm"

func prim_initialize

	nop					activate
	sel_addrreg 0  		nop
	nop					scannop
	vload 0				vload 0
	vxor 0				vxor 0
	halt				nop

endfunc

;-------------------------------------------------------------------------------------
