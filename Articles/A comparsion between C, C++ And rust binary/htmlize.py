s = """
               ; DATA XREF from main @ 0x8837
/ 317: sym.src::main::hc7c3432ba867720f ();
|           ; var int64_t var_7h @ rsp+0x7
|           ; var int64_t var_8h @ rsp+0x8
|           ; var int64_t var_10h @ rsp+0x10
|           ; var int64_t var_18h @ rsp+0x18
|           ; var int64_t var_20h @ rsp+0x20
|           ; var int64_t var_28h @ rsp+0x28
|           ; var int64_t var_30h @ rsp+0x30
|           ; var int64_t var_48h @ rsp+0x48
|           ; var int64_t var_60h @ rsp+0x60
|           ; var int64_t var_68h @ rsp+0x68
|           ; var int64_t var_70h @ rsp+0x70
|           ; var int64_t var_78h @ rsp+0x78
|           ; var int64_t var_a8h @ rsp+0xa8
|           0x000085e0      4881ece80000.  sub rsp, 0xe8               ; src::main::hc7c3432ba867720f
|           0x000085e7      488d7c2430     lea rdi, [var_30h]          ; int64_t arg1
|           0x000085ec      e8af060000     call sym alloc::string::String::new::h23e41f2cf6159f13 ; sym.alloc::string::String::new::h23e41f2cf6159f13
|           0x000085f1      488d05b81c01.  lea rax, [sym.std::io::stdio::stdin::h35f7187a3d40037d] ; 0x1a2b0 ; "PH\x8b\x05\xb0]\x03"
|           0x000085f8      ffd0           call rax
|           0x000085fa      4889442428     mov qword [var_28h], rax
|       ,=< 0x000085ff      eb00           jmp 0x8601
|       |   ; CODE XREF from src::main::hc7c3432ba867720f @ 0x85ff
|       `-> 0x00008601      488b442428     mov rax, qword [var_28h]
|           0x00008606      4889442460     mov qword [var_60h], rax
|           0x0000860b      488d05ce1c01.  lea rax, [sym.std::io::stdio::Stdin::read_line::h65cece326c44d417] ; 0x1a2e0 ; "UAWAVATSH\x83\xec\x10I\x89\xd6I\x89\xffH\x8b\x1eH\x8b;\xff\x15\xb2\\x03"
|           0x00008612      488d7c2448     lea rdi, [var_48h]
|           0x00008617      488d742460     lea rsi, [var_60h]
|           0x0000861c      488d542430     lea rdx, [var_30h]
|           0x00008621      ffd0           call rax
|       ,=< 0x00008623      eb25           jmp 0x864a
        |   ; CODE XREF from src::main::hc7c3432ba867720f @ +0x68 ; sym.src::main::hc7c3432ba867720f
..
|     | |   ; CODE XREF from src::main::hc7c3432ba867720f @ 0x8623
|     | `-> 0x0000864a      488d7c2448     lea rdi, [var_48h]          ; uint32_t arg1
|     |     0x0000864f      e80c050000     call sym core::ptr::drop_in_place<core::result::Result<usize,std::io::error::Error>>::h96a6b0773d967b0c ; sym.core::ptr::drop_in_place_core::result::Result_usize_std::io::error::Error__::h96a6b0773d967b0c
|     | ,=< 0x00008654      eb00           jmp 0x8656
|     | |   ; CODE XREF from src::main::hc7c3432ba867720f @ 0x8654
|     | `-> 0x00008656      488d7c2430     lea rdi, [var_30h]          ; int64_t arg1
|     |     0x0000865b      e880060000     call sym <alloc::string::String as core::ops::deref::Deref>::deref::h6b83c6ad84f27c48 ; sym._alloc::string::String_as_core::ops::deref::Deref_::deref::h6b83c6ad84f27c48
|     |     0x00008660      4889542418     mov qword [var_18h], rdx
|     |     0x00008665      4889442420     mov qword [var_20h], rax
|     | ,=< 0x0000866a      eb00           jmp 0x866c
|     | |   ; CODE XREF from src::main::hc7c3432ba867720f @ 0x866a
|     | `-> 0x0000866c      488b742418     mov rsi, qword [var_18h]    ; int64_t arg2
|     |     0x00008671      488b7c2420     mov rdi, qword [var_20h]    ; int64_t arg1
|     |     0x00008676      e865100000     call sym core::str::_<impl str>::trim::h9d1fea9f17338f92 ; sym.core::str::__impl_str_::trim::h9d1fea9f17338f92
|     |     0x0000867b      4889542408     mov qword [var_8h], rdx
|     |     0x00008680      4889442410     mov qword [var_10h], rax
|     | ,=< 0x00008685      eb00           jmp 0x8687
|     | |   ; CODE XREF from src::main::hc7c3432ba867720f @ 0x8685
|     | `-> 0x00008687      488b442408     mov rax, qword [var_8h]
|     |     0x0000868c      488b4c2410     mov rcx, qword [var_10h]
|     |     0x00008691      48894c2468     mov qword [var_68h], rcx
|     |     0x00008696      4889442470     mov qword [var_70h], rax
|     |     0x0000869b      488d35264b04.  lea rsi, [0x0004d1c8]       ; int64_t arg2
|     |     0x000086a2      488d7c2468     lea rdi, [var_68h]          ; int64_t arg1
|     |     0x000086a7      e834090000     call sym core::cmp::impls::_<impl core::cmp::PartialEq<&B> for &A>::eq::hcf3623dea7e76b2d ; sym.core::cmp::impls::__impl_core::cmp::PartialEq_B__for_A_::eq::hcf3623dea7e76b2d
|     |     0x000086ac      88442407       mov byte [var_7h], al
|     | ,=< 0x000086b0      eb00           jmp 0x86b2
|     | |   ; CODE XREF from src::main::hc7c3432ba867720f @ 0x86b0
|     | `-> 0x000086b2      8a442407       mov al, byte [var_7h]
|     |     0x000086b6      a801           test al, 1
|     | ,=< 0x000086b8      7529           jne 0x86e3
|     |,==< 0x000086ba      eb00           jmp 0x86bc
|     |||   ; CODE XREF from src::main::hc7c3432ba867720f @ 0x86ba
|     |`--> 0x000086bc      488d35254b04.  lea rsi, [0x0004d1e8]       ; int64_t arg2
|     | |   0x000086c3      488d0d7e5903.  lea rcx, str.Incorrect..._n ; 0x3e048 ; "Incorrect...\n" ; int64_t arg4
|     | |   0x000086ca      31c0           xor eax, eax
|     | |   0x000086cc      4189c0         mov r8d, eax                ; int64_t arg5
|     | |   0x000086cf      488dbc24a800.  lea rdi, [var_a8h]          ; int64_t arg1
|     | |   0x000086d7      ba01000000     mov edx, 1                  ; uint32_t arg3
|     | |   0x000086dc      e8ff180000     call sym core::fmt::Arguments::new_v1::haa4ce4ad7f317a29 ; sym.core::fmt::Arguments::new_v1::haa4ce4ad7f317a29
|     |,==< 0x000086e1      eb42           jmp 0x8725
|     |||   ; CODE XREF from src::main::hc7c3432ba867720f @ 0x86b8
|     ||`-> 0x000086e3      488d35ee4a04.  lea rsi, [0x0004d1d8]       ; int64_t arg2
|     ||    0x000086ea      488d0d575903.  lea rcx, str.Incorrect..._n ; 0x3e048 ; "Incorrect...\n" ; int64_t arg4
|     ||    0x000086f1      31c0           xor eax, eax
|     ||    0x000086f3      4189c0         mov r8d, eax                ; int64_t arg5
|     ||    0x000086f6      488d7c2478     lea rdi, [var_78h]          ; int64_t arg1
|     ||    0x000086fb      ba01000000     mov edx, 1                  ; uint32_t arg3
|     ||    0x00008700      e8db180000     call sym core::fmt::Arguments::new_v1::haa4ce4ad7f317a29 ; sym.core::fmt::Arguments::new_v1::haa4ce4ad7f317a29
|     ||,=< 0x00008705      eb00           jmp 0x8707
|     |||   ; CODE XREF from src::main::hc7c3432ba867720f @ 0x8705
|     ||`-> 0x00008707      488d05622201.  lea rax, [dbg._print]       ; 0x1a970 ; "UAWAVATSH\x81\xec\xb0"
|     ||    0x0000870e      488d7c2478     lea rdi, [var_78h]
|     ||    0x00008713      ffd0           call rax
|     ||,=< 0x00008715      eb00           jmp 0x8717
|     |||   ; CODE XREF from src::main::hc7c3432ba867720f @ 0x8715
|    ,==`-> 0x00008717      eb00           jmp 0x8719
|    |||    ; CODE XREFS from src::main::hc7c3432ba867720f @ 0x8717, 0x8738
|    `--.-> 0x00008719      488d7c2430     lea rdi, [var_30h]          ; int64_t arg1
|     ||:   0x0000871e      e85d030000     call sym core::ptr::drop_in_place<alloc::string::String>::h0c1c3e9775635650 ; sym.core::ptr::drop_in_place_alloc::string::String_::h0c1c3e9775635650
|    ,====< 0x00008723      eb24           jmp 0x8749
|    |||:   ; CODE XREF from src::main::hc7c3432ba867720f @ 0x86e1
|    ||`--> 0x00008725      488d05442201.  lea rax, [dbg._print]       ; 0x1a970 ; "UAWAVATSH\x81\xec\xb0"
|    || :   0x0000872c      488dbc24a800.  lea rdi, [var_a8h]
|    || :   0x00008734      ffd0           call rax
|    ||,==< 0x00008736      eb00           jmp 0x8738
|    ||||   ; CODE XREF from src::main::hc7c3432ba867720f @ 0x8736
|    ||``=< 0x00008738      ebdf           jmp 0x8719
     ||     ; CODE XREF from src::main::hc7c3432ba867720f @ +0x4f ; sym.src::main::hc7c3432ba867720f
..
|    |      ; CODE XREF from src::main::hc7c3432ba867720f @ 0x8723
|    `----> 0x00008749      4881c4e80000.  add rsp, 0xe8
\           0x00008750      c3             ret
"""

s = s.replace("&", "&amp;")
s = s.replace("<", "&lt;")
s = s.replace(">", "&gt;")

print(s)
