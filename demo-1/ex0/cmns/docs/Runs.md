[//]: # () - open build.gradle.kts and go to activeRecipe block and uncomment what rewrite is needed one after the other or all at once too
//Did work well with jdk 23.0.1 and gradle 8.10.2

gradle rewriteRun //grr
gradle rewriteDryRun //gdr
 
git restore . --source=HEAD && git clean -fd //gitrc


