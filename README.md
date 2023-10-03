# Memory Management Algorithms Project

![App Interface](https://github.com/ismaelg-avilag/os-lab05-memory-management-01/blob/main/images/app.png)

- [Introduction](#introduction)
- [Overview](#overview)
- [Getting Started](#getting-started)
- [Algorithms Implemented](#algorithms-implemented)
- [License](#license)
- [Contact](#contact)

## Introduction

This project is part of the Operating Systems course, where various memory management algorithms are implemented in Java. The implemented algorithms include:

- First Fit
- Best Fit
- Worst Fit
- Next Fit

## Overview

In this project, we read two text files. The first file contains memory partitions, where each line represents the size of a partition in kilobytes (KB). The second file contains "files" that need to be loaded into memory. Each line in this file includes the file name (e.g., hello_world.py or report.xlsx) and its size.

The application provides a graphical user interface (GUI) that displays:

1. A table showing the files read from the input file.
2. A table listing memory partitions along with their availability status (initially, all partitions are available).

Users can select one of the memory management algorithms using radio buttons. When a user clicks a button, the selected algorithm is executed, and the results are displayed in a third table, showing which memory partition each file was assigned to according to the selected algorithm. The table of memory partitions is updated to reflect their availability after the algorithm's execution.

## Getting Started
To run this project, follow these steps:

1. Clone the repository to your local machine.
2. Compile the Java source code.
3. Run the program and interact with the GUI.

## Algorithms Implemented

### First Fit

The First Fit algorithm assigns the first available memory partition that is large enough to accommodate a file. It searches the memory partitions sequentially and selects the first one that meets the file's size requirements.

### Best Fit

The Best Fit algorithm selects the memory partition that is closest in size to the file being loaded while still being large enough to accommodate it. It aims to minimize memory wastage by selecting the smallest suitable partition.

### Worst Fit

The Worst Fit algorithm assigns the file to the largest available memory partition that can accommodate it. This approach can lead to larger memory wastage but may be useful in scenarios with large files.

### Next Fit

The Next Fit algorithm is similar to the First Fit algorithm but starts searching for a suitable memory partition from the last assigned position. It continues searching sequentially until a suitable partition is found.

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Contact
If you have any questions or need further assistance, feel free to contact the project maintainer:

Ismael Avila
- Email: ismaelg.avilag@gmail.com
- GitHub: [Ismaelg-Avilag](https://github.com/ismaelg-avilag)
- LinkedIn: [Ismaelg-Avilag](https://www.linkedin.com/in/ismaelg-avilag)
