
from jinja2 import Environment, Template, PackageLoader, select_autoescape
import yaml
import os
import re


def _lower_first_char(input_str):
    return input_str[0].lower() + input_str[1:]


def _camel_to_snake(input_str):
    s1 = re.sub('(.)([A-Z][a-z]+)', r'\1_\2', input_str)
    return re.sub('([a-z0-9])([A-Z])', r'\1_\2', s1).lower()


def _cap_first_char(var_name):
    return var_name[0].upper() + var_name[1:]


def _package_to_dir(package_name):
    return package_name.replace(".", "/")


def _on_key_pressed(child, entry):
    print(entry)

    template_str = None

    if entry['type'] == "double":
        if 'sim_value' in entry:
            template_str = """            case {{entry.sim_keys[0]}}:
                m_{{lower_first_char(child.table)}}{{cap_first_char(entry.name)}} = {{entry.sim_value}};
                break;
            case {{entry.sim_keys[1]}}:
                m_{{lower_first_char(child.table)}}{{cap_first_char(entry.name)}} = -{{entry.sim_value}};
                break;
"""
        elif 'sim_incr' in entry:
            template_str = """            case {{entry.sim_keys[0]}}:
                m_{{lower_first_char(child.table)}}{{cap_first_char(entry.name)}} -= {{entry.sim_incr}};
                break;
            case {{entry.sim_keys[1]}}:
                m_{{lower_first_char(child.table)}}{{cap_first_char(entry.name)}} += {{entry.sim_incr}};
                break;
"""

    elif entry['type'] == "boolean":
        template_str = """
            case {{entry.sim_keys[0]}}:
                m_{{lower_first_char(child.table)}}{{cap_first_char(entry.name)}} = true;
                break;
"""
    if template_str is None:
        raise Exception("Unsupported type {}".format(entry['type']))

    template = _add_template_functions(Template(template_str))

    return template.render(child=child, entry=entry)


def _on_key_released(child, entry):
    print(entry)

    template_str = None

    if entry['type'] == "double":
        if 'sim_value' in entry:
            template_str = """            case {{entry.sim_keys[0]}}:
            case {{entry.sim_keys[1]}}:
                m_{{lower_first_char(child.table)}}{{cap_first_char(entry.name)}} = 0;
                break;
"""

    elif entry['type'] == "boolean":
        template_str = """
            case {{entry.sim_keys[0]}}:
                m_{{lower_first_char(child.table)}}{{cap_first_char(entry.name)}} = false;
                break;
"""
    if template_str is None:
        return ""

    template = _add_template_functions(Template(template_str))

    return template.render(child=child, entry=entry)

def _make_dir_if_not_exists(dir_name):
    if not os.path.exists(dir_name):
        os.makedirs(dir_name)


def _add_template_functions(template):
    template.globals['camel_to_snake'] = _camel_to_snake
    template.globals['lower_first_char'] = _lower_first_char
    template.globals['package_to_dir'] = _package_to_dir
    template.globals['cap_first_char'] = _cap_first_char
    template.globals['on_key_pressed'] = _on_key_pressed
    template.globals['on_key_released'] = _on_key_released

    return template

def load_template(template_name):

    template_dir = r'C:\Users\PJ\Documents\GitHub\frc2020\3504\Codelab\custom_shuffleboard\generation\lib\templates'
    template_path = os.path.join(template_dir, template_name)

    with open(template_path, 'r') as f:
        template_input = f.read()
        template = Template(template_input)
        template = _add_template_functions(template)

        return template


def dump_single_components(overall_config, widget, gen_output_dir):

    default_lookup = {}
    default_lookup["Double"] = "0.0"
    default_lookup["double"] = "0.0"
    default_lookup["Boolean"] = "false"
    default_lookup["boolean"] = "false"
    default_lookup["String"] = '""'

    data_template = load_template('data_template.txt')
    data_type_template = load_template('data_type_template.txt')

    for child in sorted(widget['children_tables'], key=lambda x: 1):
        data_dump = data_template.render(overall_config=overall_config, widget=widget, child=child, default_value_lookup=default_lookup)
        data_type_dump = data_type_template.render(overall_config=overall_config, widget=widget, child=child, default_value_lookup=default_lookup)

        full_package = overall_config['base_package'] + "." + widget["package_name"]
        package_name_as_dir = _package_to_dir(full_package)
        data_dir = os.path.join(gen_output_dir, package_name_as_dir, "data")
        _make_dir_if_not_exists(data_dir)

        data_path = os.path.join(data_dir, child["table"] + "Data.java")
        data_type_path = os.path.join(data_dir, child["table"] + "DataType.java")
        open(data_path, 'w').write(data_dump)
        open(data_type_path, 'w').write(data_type_dump)


def dump_plugin(overall_config, gen_output_dir):

    template_output = load_template('plugin_template.txt').render(overall_config=overall_config)

    package_name_as_dir = _package_to_dir(overall_config['base_package'])

    plugin_dump_dir = os.path.join(gen_output_dir, package_name_as_dir)
    _make_dir_if_not_exists(plugin_dump_dir)

    output_file = os.path.join(plugin_dump_dir, overall_config['plugin_name'] + ".java")
    print(output_file)

    open(output_file, 'w').write(template_output)


def dump_widget_top_level_data(overall_config, widget, gen_output_dir):
    template_output = load_template('parent_data_template.txt').render(overall_config=overall_config, widget=widget)

    full_package = overall_config['base_package'] + "." + widget["package_name"]
    package_name_as_dir = _package_to_dir(full_package)

    data_dir = os.path.join(gen_output_dir, package_name_as_dir, "data")
    _make_dir_if_not_exists(data_dir)
    output_file = os.path.join(data_dir, widget['table'] + "Data.java")
    # print(output_file)
    #
    open(output_file, 'w').write(template_output)


def dump_widget(overall_config, widget, gen_output_dir):
    template_output = load_template('widget_template.txt').render(overall_config=overall_config, widget=widget)

    full_package = overall_config['base_package'] + "." + widget["package_name"]
    package_name_as_dir = _package_to_dir(full_package)

    data_dir = os.path.join(gen_output_dir, package_name_as_dir)
    _make_dir_if_not_exists(data_dir)
    output_file = os.path.join(data_dir, widget['table'] + "Widget.java")
    # print(output_file)
    #
    open(output_file, 'w').write(template_output)


def dump_fxml_if_necessary(overall_config, widget, resource_dir):
    full_package = overall_config['base_package'] + "." + widget["package_name"]
    package_name_as_dir = _package_to_dir(full_package)

    data_dir = os.path.join(resource_dir, package_name_as_dir)
    _make_dir_if_not_exists(data_dir)

    controller_file = os.path.join(data_dir, _camel_to_snake(widget['table']) + ".fxml")
    widget_file = os.path.join(data_dir, _camel_to_snake(widget['table']) + "_widget.fxml")

    if not os.path.exists(widget_file):
        open(widget_file, 'w').write(load_template('fxml_widget_template.txt').render(overall_config=overall_config, widget=widget))

    if not os.path.exists(controller_file):
        open(controller_file, 'w').write(load_template('fxml_controller_template.txt').render(overall_config=overall_config, widget=widget))


def dump_controller_if_necessary(overall_config, widget, src_dir):
    full_package = overall_config['base_package'] + "." + widget["package_name"]
    package_name_as_dir = _package_to_dir(full_package)

    data_dir = os.path.join(src_dir, package_name_as_dir)
    _make_dir_if_not_exists(data_dir)

    output_file = os.path.join(data_dir, widget['table'] + "Controller.java")
    print(os.path.exists(output_file), output_file)

    open(output_file, 'w').write(load_template('controller_template.txt').render(overall_config=overall_config, widget=widget))


def dump_standalone_main_if_necessary(overall_config, widget, src_dir):
    full_package = overall_config['base_package'] + "." + widget["package_name"]
    package_name_as_dir = _package_to_dir(full_package)

    data_dir = os.path.join(src_dir, package_name_as_dir)
    output_file = os.path.join(data_dir, widget['table'] + "StandaloneMain.java")
    print(os.path.exists(output_file), output_file)

    open(output_file, 'w').write(load_template('standalone_main_template.txt').render(overall_config=overall_config, widget=widget))


def generate_dashboard_structure(config):

    gen_src_dir = os.path.abspath("../src/dashboard_gen/java")
    resource_dir = os.path.abspath("../src/main/resources")
    src_dir = os.path.abspath("../src/main/java")

    if not os.path.exists(gen_src_dir):
        raise Exception(f"The output directory '{gen_src_dir}' must exist")


    base_package_as_dir = _package_to_dir(config['base_package'])

    dump_plugin(config, gen_src_dir)

    for widget in config['widgets']:
        print(f"Running generation for widget '{widget}'")
        dump_widget_top_level_data(config, widget, gen_src_dir)
        dump_widget(config, widget, gen_src_dir)
        dump_single_components(config, widget, gen_src_dir)
        dump_fxml_if_necessary(config, widget, resource_dir)
        dump_controller_if_necessary(config, widget, src_dir)
        dump_standalone_main_if_necessary(config, widget, src_dir)

