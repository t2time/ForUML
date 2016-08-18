/*
Copyright (c) 2016, Aziz Nanthaamornphong and Anawat Leathongkum

Redistribution and use in source and binary forms, with or without modification, are 
permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of 
conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, this list of 
conditions and the following disclaimer in the documentation and/or other materials 
provided with the distribution.

3. Neither the names of the copyright holders nor the names of its contributors may be 
used to endorse or promote products derived from this software without specific prior 
written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY 
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package edu.ua.util;

import org.antlr.runtime.Token;

// Referenced classes of package fortran.ofp.parser.java:
//            IFortranParserAction, IFortranParser
public class FortranParserActionNull
        implements IFortranParserAction {

  public FortranParserActionNull(String as[], IFortranParser ifortranparser, String s) {
  }

  public void generic_name_list__begin() {
  }

  public void generic_name_list(int i) {
  }

  public void generic_name_list_part(Token token) {
  }

  public void specification_part(int i, int j, int k, int l) {
  }

  public void declaration_construct() {
  }

  public void execution_part() {
  }

  public void execution_part_construct() {
  }

  public void internal_subprogram_part(int i) {
  }

  public void internal_subprogram() {
  }

  public void specification_stmt() {
  }

  public void executable_construct() {
  }

  public void action_stmt() {
  }

  public void keyword() {
  }

  public void name(Token token) {
  }

  public void constant(Token token) {
  }

  public void scalar_constant() {
  }

  public void literal_constant() {
  }

  public void int_constant(Token token) {
  }

  public void char_constant(Token token) {
  }

  public void intrinsic_operator() {
  }

  public void defined_operator(Token token, boolean flag) {
  }

  public void extended_intrinsic_op() {
  }

  public void label(Token token) {
  }

  public void label_list__begin() {
  }

  public void label_list(int i) {
  }

  public void type_spec() {
  }

  public void type_param_value(boolean flag, boolean flag1, boolean flag2) {
  }

  public void intrinsic_type_spec(Token token, Token token1, int i, boolean flag) {
  }

  public void kind_selector(Token token, Token token1, boolean flag) {
  }

  public void signed_int_literal_constant(Token token) {
  }

  public void int_literal_constant(Token token, Token token1) {
  }

  public void kind_param(Token token) {
  }

  public void boz_literal_constant(Token token) {
  }

  public void signed_real_literal_constant(Token token) {
  }

  public void real_literal_constant(Token token, Token token1) {
  }

  public void complex_literal_constant() {
  }

  public void real_part(boolean flag, boolean flag1, Token token) {
  }

  public void imag_part(boolean flag, boolean flag1, Token token) {
  }

  public void char_selector(Token token, Token token1, int i, int j, boolean flag) {
  }

  public void length_selector(Token token, int i, boolean flag) {
  }

  public void char_length(boolean flag) {
  }

  public void scalar_int_literal_constant() {
  }

  public void char_literal_constant(Token token, Token token1, Token token2) {
  }

  public void logical_literal_constant(Token token, boolean flag, Token token1) {
  }

  public void hollerith_literal_constant(Token token) {
  }

  public void derived_type_def() {
  }

  public void type_param_or_comp_def_stmt(Token token, int i) {
  }

  public void type_param_or_comp_def_stmt_list() {
  }

  public void derived_type_stmt(Token token, Token token1, Token token2, Token token3, boolean flag, boolean flag1) {
  }

  public void type_attr_spec(Token token, Token token1, int i) {
  }

  public void type_attr_spec_list__begin() {
  }

  public void type_attr_spec_list(int i) {
  }

  public void private_or_sequence() {
  }

  public void end_type_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void sequence_stmt(Token token, Token token1, Token token2) {
  }

  public void type_param_decl(Token token, boolean flag) {
  }

  public void type_param_decl_list__begin() {
  }

  public void type_param_decl_list(int i) {
  }

  public void type_param_attr_spec(Token token) {
  }

  public void component_def_stmt(int i) {
  }

  public void data_component_def_stmt(Token token, Token token1, boolean flag) {
  }

  public void component_attr_spec(Token token, int i) {
  }

  public void component_attr_spec_list__begin() {
  }

  public void component_attr_spec_list(int i) {
  }

  public void component_decl(Token token, boolean flag, boolean flag1, boolean flag2, boolean flag3) {
  }

  public void component_decl_list__begin() {
  }

  public void component_decl_list(int i) {
  }

  public void component_array_spec(boolean flag) {
  }

  public void deferred_shape_spec_list__begin() {
  }

  public void deferred_shape_spec_list(int i) {
  }

  public void component_initialization() {
  }

  public void proc_component_def_stmt(Token token, Token token1, Token token2, boolean flag) {
  }

  public void proc_component_attr_spec(Token token, Token token1, int i) {
  }

  public void proc_component_attr_spec_list__begin() {
  }

  public void proc_component_attr_spec_list(int i) {
  }

  public void private_components_stmt(Token token, Token token1, Token token2) {
  }

  public void type_bound_procedure_part(int i, boolean flag) {
  }

  public void binding_private_stmt(Token token, Token token1, Token token2) {
  }

  public void proc_binding_stmt(Token token, int i, Token token1) {
  }

  public void specific_binding(Token token, Token token1, Token token2, Token token3, boolean flag) {
  }

  public void generic_binding(Token token, boolean flag) {
  }

  public void binding_attr(Token token, int i, Token token1) {
  }

  public void binding_attr_list__begin() {
  }

  public void binding_attr_list(int i) {
  }

  public void final_binding(Token token) {
  }

  public void derived_type_spec(Token token, boolean flag) {
  }

  public void type_param_spec(Token token) {
  }

  public void type_param_spec_list__begin() {
  }

  public void type_param_spec_list(int i) {
  }

  public void structure_constructor(Token token) {
  }

  public void component_spec(Token token) {
  }

  public void component_spec_list__begin() {
  }

  public void component_spec_list(int i) {
  }

  public void component_data_source() {
  }

  public void enum_def(int i) {
  }

  public void enum_def_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void enumerator_def_stmt(Token token, Token token1, Token token2) {
  }

  public void enumerator(Token token, boolean flag) {
  }

  public void enumerator_list__begin() {
  }

  public void enumerator_list(int i) {
  }

  public void end_enum_stmt(Token token, Token token1, Token token2, Token token3) {
  }

  public void array_constructor() {
  }

  public void ac_spec() {
  }

  public void ac_value() {
  }

  public void ac_value_list__begin() {
  }

  public void ac_value_list(int i) {
  }

  public void ac_implied_do() {
  }

  public void ac_implied_do_control(boolean flag) {
  }

  public void scalar_int_variable() {
  }

  public void type_declaration_stmt(Token token, int i, Token token1) {
  }

  public void declaration_type_spec(Token token, int i) {
  }

  public void attr_spec(Token token, int i) {
  }

  public void entity_decl(Token token, boolean flag, boolean flag1, boolean flag2, boolean flag3) {
  }

  public void entity_decl_list__begin() {
  }

  public void entity_decl_list(int i) {
  }

  public void initialization(boolean flag, boolean flag1) {
  }

  public void null_init(Token token) {
  }

  public void access_spec(Token token, int i) {
  }

  public void language_binding_spec(Token token, Token token1, boolean flag) {
  }

  public void coarray_spec(int i) {
  }

  public void array_spec(int i) {
  }

  public void array_spec_element(int i) {
  }

  public void explicit_shape_spec(boolean flag) {
  }

  public void explicit_shape_spec_list__begin() {
  }

  public void explicit_shape_spec_list(int i) {
  }

  public void intent_spec(Token token, Token token1, int i) {
  }

  public void access_stmt(Token token, Token token1, boolean flag) {
  }

  public void access_id() {
  }

  public void access_id_list__begin() {
  }

  public void access_id_list(int i) {
  }

  public void allocatable_stmt(Token token, Token token1, Token token2) {
  }

  public void allocatable_decl(Token token, boolean flag, boolean flag1) {
  }

  public void allocatable_decl_list__begin() {
  }

  public void allocatable_decl_list(int i) {
  }

  public void asynchronous_stmt(Token token, Token token1, Token token2) {
  }

  public void bind_stmt(Token token, Token token1) {
  }

  public void bind_entity(Token token, boolean flag) {
  }

  public void bind_entity_list__begin() {
  }

  public void bind_entity_list(int i) {
  }

  public void codimension_stmt(Token token, Token token1, Token token2) {
  }

  public void codimension_decl(Token token, Token token1, Token token2) {
  }

  public void codimension_decl_list__begin() {
  }

  public void codimension_decl_list(int i) {
  }

  public void data_stmt(Token token, Token token1, Token token2, int i) {
  }

  public void data_stmt_set() {
  }

  public void data_stmt_object() {
  }

  public void data_stmt_object_list__begin() {
  }

  public void data_stmt_object_list(int i) {
  }

  public void data_implied_do(Token token, boolean flag) {
  }

  public void data_i_do_object() {
  }

  public void data_i_do_object_list__begin() {
  }

  public void data_i_do_object_list(int i) {
  }

  public void data_stmt_value(Token token) {
  }

  public void data_stmt_value_list__begin() {
  }

  public void data_stmt_value_list(int i) {
  }

  public void scalar_int_constant() {
  }

  public void data_stmt_constant() {
  }

  public void dimension_stmt(Token token, Token token1, Token token2, int i) {
  }

  public void dimension_decl(Token token) {
  }

  public void intent_stmt(Token token, Token token1, Token token2) {
  }

  public void optional_stmt(Token token, Token token1, Token token2) {
  }

  public void parameter_stmt(Token token, Token token1, Token token2) {
  }

  public void named_constant_def_list__begin() {
  }

  public void named_constant_def_list(int i) {
  }

  public void named_constant_def(Token token) {
  }

  public void pointer_stmt(Token token, Token token1, Token token2) {
  }

  public void pointer_decl_list__begin() {
  }

  public void pointer_decl_list(int i) {
  }

  public void pointer_decl(Token token, boolean flag) {
  }

  public void cray_pointer_stmt(Token token, Token token1, Token token2) {
  }

  public void cray_pointer_assoc_list__begin() {
  }

  public void cray_pointer_assoc_list(int i) {
  }

  public void cray_pointer_assoc(Token token, Token token1) {
  }

  public void protected_stmt(Token token, Token token1, Token token2) {
  }

  public void save_stmt(Token token, Token token1, Token token2, boolean flag) {
  }

  public void saved_entity_list__begin() {
  }

  public void saved_entity_list(int i) {
  }

  public void saved_entity(Token token, boolean flag) {
  }

  public void target_stmt(Token token, Token token1, Token token2) {
  }

  public void target_decl(Token token, boolean flag, boolean flag1) {
  }

  public void target_decl_list__begin() {
  }

  public void target_decl_list(int i) {
  }

  public void value_stmt(Token token, Token token1, Token token2) {
  }

  public void volatile_stmt(Token token, Token token1, Token token2) {
  }

  public void implicit_stmt(Token token, Token token1, Token token2, Token token3, boolean flag) {
  }

  public void implicit_spec() {
  }

  public void implicit_spec_list__begin() {
  }

  public void implicit_spec_list(int i) {
  }

  public void letter_spec(Token token, Token token1) {
  }

  public void letter_spec_list__begin() {
  }

  public void letter_spec_list(int i) {
  }

  public void namelist_stmt(Token token, Token token1, Token token2, int i) {
  }

  public void namelist_group_name(Token token) {
  }

  public void namelist_group_object(Token token) {
  }

  public void namelist_group_object_list__begin() {
  }

  public void namelist_group_object_list(int i) {
  }

  public void equivalence_stmt(Token token, Token token1, Token token2) {
  }

  public void equivalence_set() {
  }

  public void equivalence_set_list__begin() {
  }

  public void equivalence_set_list(int i) {
  }

  public void equivalence_object() {
  }

  public void equivalence_object_list__begin() {
  }

  public void equivalence_object_list(int i) {
  }

  public void common_stmt(Token token, Token token1, Token token2, int i) {
  }

  public void common_block_name(Token token) {
  }

  public void common_block_object_list__begin() {
  }

  public void common_block_object_list(int i) {
  }

  public void common_block_object(Token token, boolean flag) {
  }

  public void variable() {
  }

  public void designator(boolean flag) {
  }

  public void designator_or_func_ref() {
  }

  public void substring_range_or_arg_list() {
  }

  public void substr_range_or_arg_list_suffix() {
  }

  public void logical_variable() {
  }

  public void default_logical_variable() {
  }

  public void scalar_default_logical_variable() {
  }

  public void char_variable() {
  }

  public void default_char_variable() {
  }

  public void scalar_default_char_variable() {
  }

  public void int_variable() {
  }

  public void substring(boolean flag) {
  }

  public void substring_range(boolean flag, boolean flag1) {
  }

  public void data_ref(int i) {
  }

  public void part_ref(Token token, boolean flag, boolean flag1) {
  }

  public void section_subscript(boolean flag, boolean flag1, boolean flag2, boolean flag3) {
  }

  public void section_subscript_list__begin() {
  }

  public void section_subscript_list(int i) {
  }

  public void vector_subscript() {
  }

  public void allocate_stmt(Token token, Token token1, Token token2, boolean flag, boolean flag1) {
  }

  public void image_selector(Token token, Token token1) {
  }

  public void alloc_opt(Token token) {
  }

  public void alloc_opt_list__begin() {
  }

  public void alloc_opt_list(int i) {
  }

  public void cosubscript_list__begin() {
  }

  public void cosubscript_list(int i, Token token) {
  }

  public void allocation(boolean flag, boolean flag1) {
  }

  public void allocation_list__begin() {
  }

  public void allocation_list(int i) {
  }

  public void allocate_object() {
  }

  public void allocate_object_list__begin() {
  }

  public void allocate_object_list(int i) {
  }

  public void allocate_shape_spec(boolean flag, boolean flag1) {
  }

  public void allocate_shape_spec_list__begin() {
  }

  public void allocate_shape_spec_list(int i) {
  }

  public void nullify_stmt(Token token, Token token1, Token token2) {
  }

  public void pointer_object() {
  }

  public void pointer_object_list__begin() {
  }

  public void pointer_object_list(int i) {
  }

  public void deallocate_stmt(Token token, Token token1, Token token2, boolean flag) {
  }

  public void dealloc_opt(Token token) {
  }

  public void dealloc_opt_list__begin() {
  }

  public void dealloc_opt_list(int i) {
  }

  public void allocate_coarray_spec() {
  }

  public void allocate_coshape_spec(boolean flag) {
  }

  public void allocate_coshape_spec_list__begin() {
  }

  public void allocate_coshape_spec_list(int i) {
  }

  public void primary() {
  }

  public void level_1_expr(Token token) {
  }

  public void defined_unary_op(Token token) {
  }

  public void power_operand(boolean flag) {
  }

  public void power_operand__power_op(Token token) {
  }

  public void mult_operand(int i) {
  }

  public void mult_operand__mult_op(Token token) {
  }

  public void signed_operand(Token token) {
  }

  public void add_operand(int i) {
  }

  public void add_operand__add_op(Token token) {
  }

  public void level_2_expr(int i) {
  }

  public void power_op(Token token) {
  }

  public void mult_op(Token token) {
  }

  public void add_op(Token token) {
  }

  public void level_3_expr(Token token) {
  }

  public void concat_op(Token token) {
  }

  public void rel_op(Token token) {
  }

  public void and_operand(boolean flag, int i) {
  }

  public void and_operand__not_op(boolean flag) {
  }

  public void or_operand(int i) {
  }

  public void equiv_operand(int i) {
  }

  public void equiv_operand__equiv_op(Token token) {
  }

  public void level_5_expr(int i) {
  }

  public void level_5_expr__defined_binary_op(Token token) {
  }

  public void not_op(Token token) {
  }

  public void and_op(Token token) {
  }

  public void or_op(Token token) {
  }

  public void equiv_op(Token token) {
  }

  public void expr() {
  }

  public void defined_binary_op(Token token) {
  }

  public void assignment_stmt(Token token, Token token1) {
  }

  public void pointer_assignment_stmt(Token token, Token token1, boolean flag, boolean flag1) {
  }

  public void data_pointer_object() {
  }

  public void bounds_spec() {
  }

  public void bounds_spec_list__begin() {
  }

  public void bounds_spec_list(int i) {
  }

  public void bounds_remapping() {
  }

  public void bounds_remapping_list__begin() {
  }

  public void bounds_remapping_list(int i) {
  }

  public void proc_pointer_object() {
  }

  public void where_stmt__begin() {
  }

  public void where_stmt(Token token, Token token1) {
  }

  public void where_construct(int i, boolean flag, boolean flag1) {
  }

  public void where_construct_stmt(Token token, Token token1, Token token2) {
  }

  public void where_body_construct() {
  }

  public void masked_elsewhere_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void masked_elsewhere_stmt__end(int i) {
  }

  public void elsewhere_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void elsewhere_stmt__end(int i) {
  }

  public void end_where_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void forall_construct() {
  }

  public void forall_construct_stmt(Token token, Token token1, Token token2, Token token3) {
  }

  public void forall_header() {
  }

  public void forall_triplet_spec(Token token, boolean flag) {
  }

  public void forall_triplet_spec_list__begin() {
  }

  public void forall_triplet_spec_list(int i) {
  }

  public void forall_body_construct() {
  }

  public void forall_assignment_stmt(boolean flag) {
  }

  public void end_forall_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void forall_stmt__begin() {
  }

  public void forall_stmt(Token token, Token token1) {
  }

  public void block() {
  }

  public void if_construct() {
  }

  public void if_then_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void else_if_stmt(Token token, Token token1, Token token2, Token token3, Token token4, Token token5) {
  }

  public void else_stmt(Token token, Token token1, Token token2, Token token3) {
  }

  public void end_if_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void if_stmt__begin() {
  }

  public void if_stmt(Token token, Token token1) {
  }

  public void block_construct() {
  }

  public void specification_part_and_block(int i, int j, int k) {
  }

  public void block_stmt(Token token, Token token1, Token token2, Token token3) {
  }

  public void end_block_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void critical_construct() {
  }

  public void critical_stmt(Token token, Token token1, Token token2, Token token3) {
  }

  public void end_critical_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void case_construct() {
  }

  public void select_case_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void case_stmt(Token token, Token token1, Token token2, Token token3) {
  }

  public void end_select_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void case_selector(Token token) {
  }

  public void case_value_range() {
  }

  public void case_value_range_list__begin() {
  }

  public void case_value_range_list(int i) {
  }

  public void case_value_range_suffix() {
  }

  public void case_value() {
  }

  public void associate_construct() {
  }

  public void associate_stmt(Token token, Token token1, Token token2, Token token3) {
  }

  public void association_list__begin() {
  }

  public void association_list(int i) {
  }

  public void association(Token token) {
  }

  public void selector() {
  }

  public void end_associate_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void select_type_construct() {
  }

  public void select_type_stmt(Token token, Token token1, Token token2, Token token3) {
  }

  public void select_type(Token token, Token token1) {
  }

  public void type_guard_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void end_select_type_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void do_construct() {
  }

  public void block_do_construct() {
  }

  public void do_stmt(Token token, Token token1, Token token2, Token token3, Token token4, boolean flag) {
  }

  public void label_do_stmt(Token token, Token token1, Token token2, Token token3, Token token4, boolean flag) {
  }

  public void loop_control(Token token, int i, boolean flag) {
  }

  public void do_variable(Token token) {
  }

  public void end_do() {
  }

  public void end_do_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void do_term_action_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void cycle_stmt(Token token, Token token1, Token token2, Token token3) {
  }

  public void exit_stmt(Token token, Token token1, Token token2, Token token3) {
  }

  public void goto_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void computed_goto_stmt(Token token, Token token1, Token token2, Token token3) {
  }

  public void assign_stmt(Token token, Token token1, Token token2, Token token3, Token token4, Token token5) {
  }

  public void assigned_goto_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void stmt_label_list() {
  }

  public void pause_stmt(Token token, Token token1, Token token2, Token token3) {
  }

  public void arithmetic_if_stmt(Token token, Token token1, Token token2, Token token3, Token token4, Token token5) {
  }

  public void continue_stmt(Token token, Token token1, Token token2) {
  }

  public void stop_stmt(Token token, Token token1, Token token2, boolean flag) {
  }

  public void stop_code(Token token) {
  }

  public void errorstop_stmt(Token token, Token token1, Token token2, Token token3, boolean flag) {
  }

  public void sync_all_stmt(Token token, Token token1, Token token2, Token token3, boolean flag) {
  }

  public void sync_stat(Token token) {
  }

  public void sync_stat_list__begin() {
  }

  public void sync_stat_list(int i) {
  }

  public void sync_images_stmt(Token token, Token token1, Token token2, Token token3, boolean flag) {
  }

  public void image_set(Token token, boolean flag) {
  }

  public void sync_memory_stmt(Token token, Token token1, Token token2, Token token3, boolean flag) {
  }

  public void lock_stmt(Token token, Token token1, Token token2, boolean flag) {
  }

  public void lock_stat(Token token) {
  }

  public void lock_stat_list__begin() {
  }

  public void lock_stat_list(int i) {
  }

  public void unlock_stmt(Token token, Token token1, Token token2, boolean flag) {
  }

  public void lock_variable() {
  }

  public void scalar_char_constant() {
  }

  public void io_unit() {
  }

  public void file_unit_number() {
  }

  public void open_stmt(Token token, Token token1, Token token2) {
  }

  public void connect_spec(Token token) {
  }

  public void connect_spec_list__begin() {
  }

  public void connect_spec_list(int i) {
  }

  public void close_stmt(Token token, Token token1, Token token2) {
  }

  public void close_spec(Token token) {
  }

  public void close_spec_list__begin() {
  }

  public void close_spec_list(int i) {
  }

  public void read_stmt(Token token, Token token1, Token token2, boolean flag) {
  }

  public void write_stmt(Token token, Token token1, Token token2, boolean flag) {
  }

  public void print_stmt(Token token, Token token1, Token token2, boolean flag) {
  }

  public void io_control_spec(boolean flag, Token token, boolean flag1) {
  }

  public void io_control_spec_list__begin() {
  }

  public void io_control_spec_list(int i) {
  }

  public void format() {
  }

  public void input_item() {
  }

  public void input_item_list__begin() {
  }

  public void input_item_list(int i) {
  }

  public void output_item() {
  }

  public void output_item_list__begin() {
  }

  public void output_item_list(int i) {
  }

  public void io_implied_do() {
  }

  public void io_implied_do_object() {
  }

  public void io_implied_do_control(boolean flag) {
  }

  public void dtv_type_spec(Token token) {
  }

  public void wait_stmt(Token token, Token token1, Token token2) {
  }

  public void wait_spec(Token token) {
  }

  public void wait_spec_list__begin() {
  }

  public void wait_spec_list(int i) {
  }

  public void backspace_stmt(Token token, Token token1, Token token2, boolean flag) {
  }

  public void endfile_stmt(Token token, Token token1, Token token2, Token token3, boolean flag) {
  }

  public void rewind_stmt(Token token, Token token1, Token token2, boolean flag) {
  }

  public void position_spec(Token token) {
  }

  public void position_spec_list__begin() {
  }

  public void position_spec_list(int i) {
  }

  public void flush_stmt(Token token, Token token1, Token token2, boolean flag) {
  }

  public void flush_spec(Token token) {
  }

  public void flush_spec_list__begin() {
  }

  public void flush_spec_list(int i) {
  }

  public void inquire_stmt(Token token, Token token1, Token token2, Token token3, boolean flag) {
  }

  public void inquire_spec(Token token) {
  }

  public void inquire_spec_list__begin() {
  }

  public void inquire_spec_list(int i) {
  }

  public void format_stmt(Token token, Token token1, Token token2) {
  }

  public void format_specification(boolean flag) {
  }

  public void format_item(Token token, boolean flag) {
  }

  public void format_item_list__begin() {
  }

  public void format_item_list(int i) {
  }

  public void v_list_part(Token token, Token token1) {
  }

  public void v_list__begin() {
  }

  public void v_list(int i) {
  }

  public void main_program__begin() {
  }

  public void main_program(boolean flag, boolean flag1, boolean flag2) {
  }

  public void ext_function_subprogram(boolean flag) {
  }

  public void program_stmt(Token token, Token token1, Token token2, Token token3) {
  }

  public void end_program_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void module() {
  }

  public void module_stmt__begin() {
  }

  public void module_stmt(Token token, Token token1, Token token2, Token token3) {
  }

  public void end_module_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void module_subprogram_part(int i) {
  }

  public void module_subprogram(boolean flag) {
  }

  public void use_stmt(Token token, Token token1, Token token2, Token token3, Token token4, boolean flag, boolean flag1,
          boolean flag2) {
  }

  public void module_nature(Token token) {
  }

  public void rename(Token token, Token token1, Token token2, Token token3, Token token4, Token token5) {
  }

  public void rename_list__begin() {
  }

  public void rename_list(int i) {
  }

  public void only(boolean flag, boolean flag1, boolean flag2) {
  }

  public void only_list__begin() {
  }

  public void only_list(int i) {
  }

  public void submodule(boolean flag) {
  }

  public void submodule_stmt__begin() {
  }

  public void submodule_stmt(Token token, Token token1, Token token2, Token token3) {
  }

  public void parent_identifier(Token token, Token token1) {
  }

  public void end_submodule_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void block_data() {
  }

  public void block_data_stmt__begin() {
  }

  public void block_data_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void end_block_data_stmt(Token token, Token token1, Token token2, Token token3, Token token4, Token token5) {
  }

  public void interface_block() {
  }

  public void interface_specification() {
  }

  public void interface_stmt__begin() {
  }

  public void interface_stmt(Token token, Token token1, Token token2, Token token3, boolean flag) {
  }

  public void end_interface_stmt(Token token, Token token1, Token token2, Token token3, boolean flag) {
  }

  public void interface_body(boolean flag) {
  }

  public void procedure_stmt(Token token, Token token1, Token token2, Token token3) {
  }

  public void generic_spec(Token token, Token token1, int i) {
  }

  public void dtio_generic_spec(Token token, Token token1, int i) {
  }

  public void import_stmt(Token token, Token token1, Token token2, boolean flag) {
  }

  public void external_stmt(Token token, Token token1, Token token2) {
  }

  public void procedure_declaration_stmt(Token token, Token token1, Token token2, boolean flag, int i) {
  }

  public void proc_interface(Token token) {
  }

  public void proc_attr_spec(Token token, Token token1, int i) {
  }

  public void proc_decl(Token token, boolean flag) {
  }

  public void proc_decl_list__begin() {
  }

  public void proc_decl_list(int i) {
  }

  public void intrinsic_stmt(Token token, Token token1, Token token2) {
  }

  public void function_reference(boolean flag) {
  }

  public void call_stmt(Token token, Token token1, Token token2, boolean flag) {
  }

  public void procedure_designator() {
  }

  public void actual_arg_spec(Token token) {
  }

  public void actual_arg_spec_list__begin() {
  }

  public void actual_arg_spec_list(int i) {
  }

  public void actual_arg(boolean flag, Token token) {
  }

  public void function_subprogram(boolean flag, boolean flag1) {
  }

  public void function_stmt__begin() {
  }

  public void function_stmt(Token token, Token token1, Token token2, Token token3, boolean flag, boolean flag1) {
  }

  public void proc_language_binding_spec() {
  }

  public void prefix(int i) {
  }

  public void t_prefix(int i) {
  }

  public void prefix_spec(boolean flag) {
  }

  public void t_prefix_spec(Token token) {
  }

  public void suffix(Token token, boolean flag) {
  }

  public void result_name() {
  }

  public void end_function_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void subroutine_stmt__begin() {
  }

  public void subroutine_stmt(Token token, Token token1, Token token2, Token token3, boolean flag, boolean flag1, boolean flag2,
          boolean flag3) {
  }

  public void dummy_arg(Token token) {
  }

  public void dummy_arg_list__begin() {
  }

  public void dummy_arg_list(int i) {
  }

  public void end_subroutine_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void entry_stmt(Token token, Token token1, Token token2, Token token3, boolean flag, boolean flag1) {
  }

  public void return_stmt(Token token, Token token1, Token token2, boolean flag) {
  }

  public void contains_stmt(Token token, Token token1, Token token2) {
  }

  public void separate_module_subprogram(boolean flag, boolean flag1) {
  }

  public void separate_module_subprogram__begin() {
  }

  public void mp_subprogram_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void end_mp_subprogram_stmt(Token token, Token token1, Token token2, Token token3, Token token4) {
  }

  public void stmt_function_stmt(Token token, Token token1, Token token2, boolean flag) {
  }

  public void end_of_stmt(Token token) {
  }

  public void start_of_file(String s, String s1) {
  }

  public void end_of_file(String s, String s1) {
  }

  public void cleanUp() {
  }

  public void rice_image_selector(Token token) {
  }

  public void rice_co_dereference_op(Token token, Token token1) {
  }

  public void rice_allocate_coarray_spec(int i, Token token) {
  }

  public void rice_co_with_team_stmt(Token token, Token token1) {
  }

  public void rice_end_with_team_stmt(Token token, Token token1, Token token2) {
  }

  public void rice_finish_stmt(Token token, Token token1, Token token2) {
  }

  public void rice_end_finish_stmt(Token token, Token token1) {
  }

  public void rice_spawn_stmt(Token token, Token token1, Token token2, boolean flag) {
  }

  public void next_token(Token token) {
  }
}