//-------------------------------------------------------------------------------------
//
//                             The XRT Project
//
// See LICENSE.TXT for details.
//
//-------------------------------------------------------------------------------------
#include <targets/common/Target.h>

#include <cstdio>

//-------------------------------------------------------------------------------------
void Target::writeCode(uint32_t _address, uint32_t* _code, uint32_t _length) {
    printf("Target.loadCode @%d, length=%d\n", _address, _length);
}

//-------------------------------------------------------------------------------------
