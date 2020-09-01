import yaml
import os
from lib.generate_dashboard_structure import generate_dashboard_structure

def main():

    config = yaml.load(open("dashboard_config.yml", 'r'), Loader=yaml.SafeLoader)
    generate_dashboard_structure(config)

if __name__ == "__main__":
    main()