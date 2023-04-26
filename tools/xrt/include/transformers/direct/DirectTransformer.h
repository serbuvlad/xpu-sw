//-------------------------------------------------------------------------------------
//
//                             The XRT Project
//
// See LICENSE.TXT for details.
//
//-------------------------------------------------------------------------------------
#pragma once

#include <string>
#include <manager/Manager.h>
#include <transformers/common/Transformer.h>

//-------------------------------------------------------------------------------------
class DirectTransformer : public Transformer {
    Manager *manager;
public:
    DirectTransformer(Manager *_manager);

    ~DirectTransformer() override = default;

    void load(const std::string& _path);

    void run(const std::string& _name);

    void uploadFunction(const std::string& _name, int32_t _address);

    void writeCode(uint32_t _address, uint32_t *_code, uint32_t _length);

    void readControllerData(uint32_t _address, uint32_t *_data, uint32_t _lineStart, uint32_t _lineStop,
            uint32_t _columnStart, uint32_t _columnStop);

    void writeControllerData(uint32_t _address, uint32_t *_data, uint32_t _lineStart, uint32_t _lineStop,
            uint32_t _columnStart, uint32_t _columnStop);

    void
    readArrayData(uint32_t _address, uint32_t *_data, uint32_t _lineStart, uint32_t _lineStop, uint32_t _columnStart,
            uint32_t _columnStop);

    void
    writeArrayData(uint32_t _address, uint32_t *_data, uint32_t _lineStart, uint32_t _lineStop, uint32_t _columnStart,
            uint32_t _columnStop);

    void dump(const std::string& _address);
};
//-------------------------------------------------------------------------------------
