# mknotes

A simple command line application to generate a website containing all your school notes.

# Installation

Download the .jar file [here](https://github.com/gregoryalary/mknotes/raw/master/artifacts/mknotes.jar).
Add an alias in your `~/.bashrc` file : `alias mknotes="java -jar "~/path/to/mknotes.jar""`.
Open a new terminal and type `mknotes -V` to make sure that everything works.

# Usage

Use the `-c` or `--create` options to create a new note directory as such `mknotes -c="My notes"`.

Move into the freshly created directory and start taking your notes in markdown in the `notes/` directory.
You can create as much directory and file in the `notes/` directory.
Exemple :
```
notes
├── Data structures
│   ├── B-Tree.md
│   └── Stacks.md
├── Learning code the hard way.md
└── Learning python
    ├── How to create a function.md
    ├── Plots.md
    └── Variables.md
```

Use the `-s` or `--site` options to export your notes. The website will be located in the 'site/` directory.

# Enjoy !
