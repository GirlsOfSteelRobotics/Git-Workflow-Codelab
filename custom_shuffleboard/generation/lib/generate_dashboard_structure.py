
from jinja2 import Environment, Template, PackageLoader, select_autoescape
import yaml
import os


def load_template(template_name):

    template_dir = r'C:\Users\PJ\Documents\GitHub\frc2020\3504\Codelab\custom_shuffleboard\generation\lib\templates'
    template_path = os.path.join(template_dir, template_name)

    with open(template_path, 'r') as f:
        output = f.read()
        return output


def dump_single_components(overall_config, widget, output_dir):

    default_lookup = {}
    default_lookup["Double"] = "0.0"
    default_lookup["double"] = "0.0"
    default_lookup["Boolean"] = "false"
    default_lookup["boolean"] = "false"
    default_lookup["String"] = '""'

    data_template = load_template('data_template.txt')
    data_type_template = load_template('data_type_template.txt')

    for child in sorted(widget['children_tables'], key=lambda x: 1):
        data_dump = Template(data_template).render(overall_config=overall_config, child=child, default_value_lookup=default_lookup)
        data_type_dump = Template(data_type_template).render(overall_config=overall_config, child=child, default_value_lookup=default_lookup)

        full_package = overall_config['base_package'] + "." + child["package_name"]
        package_name_as_dir = _package_to_dir(full_package)
        data_dir = os.path.join(output_dir, package_name_as_dir, "data")
        _make_dir_if_not_exists(data_dir)

        data_path = os.path.join(data_dir, child["table"] + "Data.java")
        data_type_path = os.path.join(data_dir, child["table"] + "DataType.java")
        open(data_path, 'w').write(data_dump)
        open(data_type_path, 'w').write(data_type_dump)


def dump_plugin(overall_config, output_dir):

    template = load_template('plugin_template.txt')

    template_output = Template(template).render(overall_config=overall_config)

    package_name_as_dir = _package_to_dir(overall_config['base_package'])

    output_file = os.path.join(output_dir, package_name_as_dir, "GirlsOfSteelRobotPlugin2020.java")
    print(output_file)

    open(output_file, 'w').write(template_output)


def _package_to_dir(package_name):
    return package_name.replace(".", "/")

def _make_dir_if_not_exists(dir_name):
    if not os.path.exists(dir_name):
        os.makedirs(dir_name)


def generate_dashboard_structure(config, output_dir):

    if not os.path.exists(output_dir):
        raise Exception(f"The output directory '{output_dir}' must exist")


    base_package_as_dir = _package_to_dir(config['base_package'])

    dump_plugin(config, output_dir)

    for widget in config['widgets']:
        print(f"Running generation for widget '{widget}'")
        dump_single_components(config, widget, output_dir)



    # src_dir = os.path.abspath("src/main/java")
    # config = yaml.load(open("dashboard.yml", 'r'), Loader=yaml.SafeLoader)

    # default_lookup = {}
    # default_lookup["Double"] = "0.0"
    # default_lookup["double"] = "0.0"
    # default_lookup["Boolean"] = "false"
    # default_lookup["boolean"] = "false"
    # default_lookup["String"] = '""'

    #
    #         data_dump = Template(DATA_TEMPLATE).render(child=child,
    #                                                    default_value_lookup=default_lookup)
    #
    #         package_name_as_dir = child["package_name"].replace(".", "/")
    #         data_dir = os.path.join(src_dir, package_name_as_dir, "data")
    #         data_path = os.path.join(data_dir, child["table"] + "Data.java")
    #         data_type_path = os.path.join(data_dir, child["table"] + "DataType.java")
    #         open(data_path, 'w').write(data_dump)
    #         open(data_type_path, 'w').write(data_type_dump)

