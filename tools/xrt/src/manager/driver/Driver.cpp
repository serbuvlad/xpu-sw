//-------------------------------------------------------------------------------------
//
//                             The XRT Project
//
// See LICENSE.TXT for details.
//
//-------------------------------------------------------------------------------------
#include <cstdint>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <string>
#include <sys/types.h>
#include "manager/driver/Driver.h"

//-------------------------------------------------------------------------------------
Driver::Driver(Targets *_targets)
    : targets(_targets) {

#ifndef XRT_NO_FPGA_TARGET
    int memory_file_descriptor = open("/dev/mem", O_RDWR | O_SYNC);

    io_matrix_n = 64;

    io_matrix = (uint32_t *) mmap(nullptr, io_matrix_n * sizeof(uint32_t), PROT_READ | PROT_WRITE, MAP_SHARED,
            memory_file_descriptor, 0x19100000);
#endif

}

//-------------------------------------------------------------------------------------
void Driver::writeMatrixArray(uint32_t *_ramMatrix,
                              uint32_t _ramLineSize, uint32_t _ramColumnSize,
                              uint32_t _ramStartLine, uint32_t _ramStartColumn,
                              uint32_t _numLine, uint32_t _numColumn,
                              uint32_t _accMemStart) {


    // TODO: test performance of liniarization vs sending each part individually on FIFO

    /*if (io_matrix_n < _numLine * _numColumn) {
        io_matrix_n = _numLine * _numColumn;

        if (io_matrix != nullptr) {
            delete [] io_matrix;
        }

        io_matrix = new uint32_t[io_matrix_n];
    } */

    uint32_t io_matrix_i = 0;

    for (uint32_t i = _ramStartLine; i < _ramStartLine + _numLine; i++ ) {
        for (uint32_t j = _ramStartColumn; j < _ramStartColumn + _numColumn; j++) {
            io_matrix[io_matrix_i++] = _ramMatrix[i * _ramColumnSize + j];
        }
    }

    writeArrayData(_accMemStart, reinterpret_cast<uint32_t*>(0x19100000), 0, _numLine, 0, _numColumn);

    std::memset(io_matrix, 0, io_matrix_n * sizeof(uint32_t));
}

//-------------------------------------------------------------------------------------
void Driver::readMatrixArray(uint32_t _accMemStart,
                             uint32_t _numLine, uint32_t _numColumn,
                             bool     _accRequireResultReady,
                             uint32_t *_ramMatrix,
                             uint32_t _ramLineSize, uint32_t _ramColumnSize,
                             uint32_t _ramStartLine, uint32_t _ramStartColumn) {

    /*if (io_matrix_n < _numLine * _numColumn) {
        io_matrix_n = _numLine * _numColumn;

        if (io_matrix != nullptr) {
            delete [] io_matrix;
        }

        io_matrix = new uint32_t[io_matrix_n];
    }*/


    assert(_accRequireResultReady == false);

    readArrayData(_accMemStart, reinterpret_cast<uint32_t*>(0x19100000), 0, _numLine, 0, _numColumn);

    uint32_t io_matrix_i = 0;

    for (uint32_t i = _ramStartLine; i < _ramStartLine + _numLine; i++ ) {
        for (uint32_t j = _ramStartColumn; j < _ramStartColumn + _numColumn; j++) {
            _ramMatrix[i * _ramColumnSize + j] = io_matrix[io_matrix_i++];
        }
    }
}

//-------------------------------------------------------------------------------------
void Driver::reset() {
    targets->reset();
}

//-------------------------------------------------------------------------------------
void Driver::runRuntime(uint32_t _address, uint32_t _argc, uint32_t *_args) {
    targets->runRuntime(_address, _argc, _args);
}

//-------------------------------------------------------------------------------------
void Driver::runDebug(uint32_t _address, uint32_t *_args, uint32_t _breakpointAddress) {
    targets->runDebug(_address, _args, _breakpointAddress);
}

//-------------------------------------------------------------------------------------
void Driver::readRegister(uint32_t _address, uint32_t _register) {
    targets->readRegister(_address, _register);
}

//-------------------------------------------------------------------------------------
void Driver::writeRegister(uint32_t _address, uint32_t _register) {
    targets->writeRegister(_address, _register);
}

//-------------------------------------------------------------------------------------
void Driver::writeCode(uint32_t _address, uint32_t *_code, uint32_t _length) {
    targets->writeCode(_address, _code, _length);
}

//-------------------------------------------------------------------------------------
void Driver::readControllerData(uint32_t _address, uint32_t *_data, uint32_t _lineStart, uint32_t _lineStop,
        uint32_t _columnStart, uint32_t _columnStop) {
    targets->readControllerData(_address, _data, _lineStart, _lineStop, _columnStart, _columnStop);
}

//-------------------------------------------------------------------------------------
void Driver::writeControllerData(uint32_t _address, uint32_t *_data, uint32_t _lineStart, uint32_t _lineStop,
        uint32_t _columnStart, uint32_t _columnStop) {
    //  printf("Driver.loadCode @%d, length=%d\n", _address, _length);
    targets->writeControllerData(_address, _data, _lineStart, _lineStop, _columnStart, _columnStop);
}

//-------------------------------------------------------------------------------------
void Driver::readArrayData(uint32_t _address, uint32_t *_data, uint32_t _lineStart, uint32_t _lineStop,
        uint32_t _columnStart, uint32_t _columnStop) {
    targets->readArrayData(_address, _data, _lineStart, _lineStop, _columnStart, _columnStop);
}

//-------------------------------------------------------------------------------------
void Driver::writeArrayData(uint32_t _address, uint32_t *_data, uint32_t _lineStart, uint32_t _lineStop,
        uint32_t _columnStart, uint32_t _columnStop) {
    //  printf("Driver.loadCode @%d, length=%d\n", _address, _length);
    targets->writeArrayData(_address, _data, _lineStart, _lineStop, _columnStart, _columnStop);
}

//-------------------------------------------------------------------------------------
void Driver::dump(const std::string &_address) {
    targets->dump(_address);
}

//-------------------------------------------------------------------------------------
