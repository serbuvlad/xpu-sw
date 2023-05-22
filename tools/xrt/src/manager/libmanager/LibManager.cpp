//-------------------------------------------------------------------------------------
//
//                             The XRT Project
//
// See LICENSE.TXT for details.
//
//-------------------------------------------------------------------------------------
#include <any>
#include <iostream>
#include <cstdint>
#include <string>
#include <ostream>
#include <manager/libmanager/LibManager.h>
#include <common/Utils.h>

//-------------------------------------------------------------------------------------
LibManager::LibManager(MemManager *_memManager, const Arch& _arch) :
    arch(_arch), memManager(_memManager) {
    std::cout << "Loading libraries..." << std::endl;

    internalLibraryLoader = new InternalLibraryLoader(_arch);
    hexLibraryLoader = new HexLibraryLoader();
    jsonLibraryLoader = new JsonLibraryLoader();
}

//-------------------------------------------------------------------------------------
void LibManager::load(const std::string &_path) {
    int _fileType = getFileTypeFromGeneralPath(_path);

    std::cout << "Loading library file " << _path << std::endl;

    switch (_fileType) {
        case XPU_FILE_HEX: {
            hexLibraryLoader->load(_path);
            break;
        }

        case XPU_FILE_JSON: {
            jsonLibraryLoader->load(_path);
            //jsonTransformer->run("main");
            break;
        }

        case XPU_FILE_OBJ: {
            throw std::runtime_error("Unimplemented");
            break;
        }

        case XPU_FILE_ONNX: {
            throw std::runtime_error("Unimplemented ONNX library");
            //onnxTransformer->load(_path);
            //onnxTransformer->run("main");
            break;
        }

        default: {
            throw std::runtime_error("Unknown file type: " + _path);
        }
    }
}

//-------------------------------------------------------------------------------------
FunctionInfo *LibManager::resolve(const std::string &_name) {
    std::cout << "Resolving function " << _name << std::endl;

    FunctionInfo *_functionInfo = internalLibraryLoader->resolve(_name);
    if (_functionInfo != nullptr) {
        return _functionInfo;
    }

    _functionInfo = hexLibraryLoader->resolve(_name);
    if (_functionInfo != nullptr) {
        return _functionInfo;
    }

    _functionInfo = jsonLibraryLoader->resolve(_name);
    if (_functionInfo != nullptr) {
       return _functionInfo;
    }

    return nullptr;
}

//-------------------------------------------------------------------------------------
uint32_t LibManager::uploadFunction(const std::string &_name) {
    FunctionInfo *_functionInfo = resolve(_name);
    if (_functionInfo->address == 0xFFFF'FFFF) {
        _functionInfo->address = uploadCode(_functionInfo->code, _functionInfo->length);
    }
    return _functionInfo->address;
}

//-------------------------------------------------------------------------------------
std::vector<FunctionInfo>& LibManager::stickyFunctionsToLoad()
{
    return internalLibraryLoader->stickyFunctionsToLoad();
}

//-------------------------------------------------------------------------------------
uint32_t LibManager::uploadCode(uint32_t *_code, uint32_t _length) {
    //TODO
    return 0;
}

//-------------------------------------------------------------------------------------
uint32_t LibManager::uploadData(uint32_t *_data, uint32_t _length) {
    //TODO
    return 0;
}

//-------------------------------------------------------------------------------------
