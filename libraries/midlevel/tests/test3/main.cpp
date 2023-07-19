#include <cstdint>
#include <xrt.h>
#include <xpu_functions.h>
#include <stddef.h>
#include <stdio.h>
#include <unistd.h>
#include <iostream>
#include <cinttypes>

int main(int argc, char **argv)
{
    XRT_CONTEX_HANDLE ctx = xpu_init(true, false, false);

    xpu_load(ctx, "prim_initialize");
    xpu_load(ctx, "prim_set_addr_regs");
    xpu_load(ctx, "prim_wait_matrices");
    xpu_load(ctx, "prim_set_res_ready");
    xpu_load(ctx, "prim_set_interrupt");
    xpu_load(ctx, "prim_fpga_test_3");

    XRT_FUNCTION_HANDLE prim_initialize = xpu_lowLevel(ctx, "prim_initialize");
    XRT_FUNCTION_HANDLE prim_set_addr_regs = xpu_lowLevel(ctx, "prim_set_addr_regs");
    XRT_FUNCTION_HANDLE wait_matrix = xpu_lowLevel(ctx, "prim_wait_matrices");
    XRT_FUNCTION_HANDLE set_result_ready = xpu_lowLevel(ctx, "prim_set_res_ready");
    XRT_FUNCTION_HANDLE set_interrupt = xpu_lowLevel(ctx, "prim_set_interrupt");
    XRT_FUNCTION_HANDLE prim_fpga_test_3 = xpu_lowLevel(ctx, "prim_fpga_test_3");

    uint32_t addr = 100;

    if (argc > 1) {
        addr = atoi(argv[1]);
    }

    xpu_runRuntime(ctx, prim_initialize, 0, NULL);
    
    uint32_t argv_set_addr_regs[2] = {addr, addr};
    xpu_runRuntime(ctx, prim_set_addr_regs, 2, argv_set_addr_regs);

    uint32_t matrix_in[16];
    for (int i = 0; i < 16; i++) {
        matrix_in[i] = i * 100;
    }

    xpu_writeMatrixArray(ctx, addr, matrix_in, 1, 16,
                         0, 0, 1, 16);
    
    uint32_t arg_wait_matrix = 1;
    xpu_runRuntime(ctx, wait_matrix, 1, &arg_wait_matrix);

    xpu_runRuntime(ctx, prim_fpga_test_3, 0, NULL);

    xpu_runRuntime(ctx, prim_fpga_test_3, 0, NULL);
    xpu_runRuntime(ctx, set_result_ready, 0, NULL);

    uint32_t matrix_out[16];
    xpu_readMatrixArray(ctx, addr, matrix_out, 1, 16,
                        0, 0, 1, 16, true);

    bool ok = true;

    for (int i = 0; i < 16; i++) {
        printf("%d\n", matrix_out[i]);

        if (matrix_out[i] != matrix_in[i] + i * 2) {
            fprintf(stderr, "Error at address %" PRIu32 ", cell %" PRIu32 ": %" PRIu32 " != %" PRIu32 "\n", addr, i, matrix_out[i], matrix_in[i] + i * 2);

            ok = false;
        }
    }

    xpu_runRuntime(ctx, set_interrupt, 0, NULL);
    printf("Status reg: %d\n", xpu_readRegister(ctx, 0x10));

    xpu_close(ctx);

    return ok ? 0 : 1;
}
