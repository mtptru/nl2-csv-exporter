# CSV exporter for Nolimits 2 STANDARD

A simple program to extract the spline of
a coaster into a CSV-file like it is
possible with the professional license of Nolimits 2.

This can be especially useful for custom track and is achieved by extracting the spline out of the model file of a light-pattern-creator export (a Lightwave-object file).

This is the equivalent of using "Export track spline" with Nolimits 2 professional, however, the spline will not be identical as you cannot choose the spacing of the vertices with this solution (TODO).

## Build from source

- **Windows:** double-click `build.bat` (or run it from a terminal). It compiles the sources and produces `NLCSV.jar` in the project root.
- **macOS / Linux:** run `sh build.sh`.

Once built, run it with `java -jar NLCSV.jar`, or on Windows just double-click `run.bat` (which builds the jar first if it is missing).

Every push is also built automatically by GitHub Actions, and tagged releases (`v*`) attach a ready-to-run `NLCSV.jar` to the GitHub Release.

## How to use

1. [Download the latest release](https://github.com/mtptru/nolimits2-csv-exporter/releases/latest) (`NLCSV.jar`), or build it yourself (see [Build from source](#build-from-source)). Running the jar only requires Java to be installed.

2. Open the coaster you want to export in Nolimits 2 and go to Advanced>Light Pattern Creator.

3. Input a file prefix, tick "Generate SCO", as well as "T", "B", "L" and "R" at the bottom and make sure that the X and Y Offset are both 0.

4. Hit "Generate" and choose an output folder.

5. Open the program (You must have Java installed. If you haven't, download it [here](https://www.oracle.com/java/technologies/downloads/)).

6. Open the .lwo file in the previously selected output folder as the Light Pattern Creator File of the program.

7. Choose an output path for your CSV file.

8. Press "Convert" and a CSV file will be generated that can be used in Programs such as Blender (with the right importer; you can find one [here](https://github.com/geforcefan/BlenderNoLimitsCSVImporter)).

## Left/right rail splines

If you want separate splines for the two rails instead of just the center spline, set the **Rail Distance** field to the radial (lateral) distance of each rail from the center spline before pressing "Convert".

When Rail Distance is greater than 0, two extra files are written next to your chosen CSV:

- `<name>_left.csv`
- `<name>_right.csv`

Each rail position is the center position shifted along the track's local left/right direction at that point. Because that direction rolls and banks with the track, the two rails stay parallel to the center spline — and to each other — at every point, including through turns and banked sections. Leaving Rail Distance at `0` keeps the original behaviour (center spline only). This is particularly useful when there are no track inversions or significant banks or when you are modeling for 3d printing and other modeling applications.
