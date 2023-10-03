import java.util.ArrayList;

public class MemoryManagementAlgorithms {

    public static ArrayList<String> FirstFit(ArrayList<MemoryPartition> memoryPartitions, ArrayList<File> files)
    {
        ArrayList<String> output = new ArrayList<>();

        for(File file : files)
            for(int i=0; i < memoryPartitions.size(); i++) {
                MemoryPartition memoryPartition = memoryPartitions.get(i);

                if(memoryPartition.isFree() && memoryPartition.getSize() >= file.getSize()) {
                    memoryPartition.setFree(false);
                    output.add(i + "," + memoryPartition.getSize() + " kb," + file.getName() + "," + file.getSize() + " kb");
                    break;
                }
            }

        return output;
    }

    public static ArrayList<String> BestFit(ArrayList<MemoryPartition> memoryPartitions, ArrayList<File> files)
    {
        ArrayList<String> output = new ArrayList<>();

        for(File file : files) {
            int bestFitIndex = -1;

            for(int i=0; i < memoryPartitions.size(); i++) {
                MemoryPartition memoryPartition = memoryPartitions.get(i);

                if(memoryPartition.isFree() && memoryPartition.getSize() >= file.getSize()) {
                    if(bestFitIndex == -1)
                        bestFitIndex = i;
                    else if(memoryPartitions.get(bestFitIndex).getSize() > memoryPartition.getSize())
                        bestFitIndex = i;
                }
            }

            if(bestFitIndex != -1) {
                output.add(bestFitIndex + "," + memoryPartitions.get(bestFitIndex).getSize() + " kb," + file.getName() + "," + file.getSize() + " kb");
                memoryPartitions.get(bestFitIndex).setFree(false);
            }
        }

        return output;
    }

    public static ArrayList<String> WorstFit(ArrayList<MemoryPartition> memoryPartitions, ArrayList<File> files)
    {
        ArrayList<String> output = new ArrayList<>();

        for(File file : files) {
            int worstFitIndex = -1;

            for(int i=0; i < memoryPartitions.size(); i++) {
                MemoryPartition memoryPartition = memoryPartitions.get(i);

                if(memoryPartition.isFree() && memoryPartition.getSize() >= file.getSize()) {
                    if(worstFitIndex == -1)
                        worstFitIndex = i;
                    else if(memoryPartitions.get(worstFitIndex).getSize() < memoryPartition.getSize())
                        worstFitIndex = i;
                }
            }

            if(worstFitIndex != -1) {
                output.add(worstFitIndex + "," + memoryPartitions.get(worstFitIndex).getSize() + " kb," + file.getName() + "," + file.getSize() + " kb");
                memoryPartitions.get(worstFitIndex).setFree(false);
            }
        }

        return output;
    }

    public static ArrayList<String> NextFit(ArrayList<MemoryPartition> memoryPartitions, ArrayList<File> files)
    {
        ArrayList<String> output = new ArrayList<>();
        int nextFitIndex = 0;

        for(File file : files) {

            for(int i=nextFitIndex; i < memoryPartitions.size(); i++) {
                MemoryPartition memoryPartition = memoryPartitions.get(i);

                if(memoryPartition.isFree() && memoryPartition.getSize() >= file.getSize()) {
                    memoryPartition.setFree(false);
                    output.add(i + "," + memoryPartition.getSize() + " kb," + file.getName() + "," + file.getSize() + " kb");

                    nextFitIndex = (i + 1) == memoryPartitions.size() ? 0 : i;
                    break;
                }
            }
        }

        return output;
    }

}
