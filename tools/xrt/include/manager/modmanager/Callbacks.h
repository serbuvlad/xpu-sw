//-------------------------------------------------------------------------------------
//
//                             The XRT Project
//
// See LICENSE.TXT for details.
//
//-------------------------------------------------------------------------------------
#pragma once

#include <cstdint>
#include <manager/Manager.h>

extern "C"
void xpu_load(void *_ctx, const char *_path);

extern "C"
void xpu_runRuntime(void *_ctx, void *_functionPtr, uint32_t _argc, uint32_t *_argv);

extern "C"
void *xpu_lowLevel(void *_ctx, const char *_path);

extern "C"
void xpu_writeMatrixArray(void *_ctx, uint32_t *_ramMatrix,
                              uint32_t _ramTotalLines, uint32_t _ramTotalColumns,
                              uint32_t _ramStartLine, uint32_t _ramStartColumn,
                              uint32_t _numLine, uint32_t _numColumn,
                              uint32_t _accMemStart);

extern "C"
void xpu_readMatrixArray(void *_ctx, uint32_t _accMemStart,
                             uint32_t _numLine, uint32_t _numColumn,
                             int      _accRequireResultReady,
                             uint32_t *_ramMatrix,
                             uint32_t _ramTotalLines, uint32_t _ramTotalColumns,
                             uint32_t _ramStartLine, uint32_t _ramStartColumn);
