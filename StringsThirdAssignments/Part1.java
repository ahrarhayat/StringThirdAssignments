

import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
  public int findStopCodon(String dna, int startIndex, String stopCodon )
    {
       
        int currIndex=dna.indexOf(stopCodon,startIndex+3);
        
        while(currIndex!=-1)
        {
            int diff=startIndex-currIndex;
           if(diff%3==0)
           {
               return currIndex;
            }
            else
            {
                currIndex=dna.indexOf(stopCodon,currIndex+1);
            }
            
        }
  
        return -1;
    }
    
    public String FindGene(String dna, int where)
    {
        
        int startIndex=dna.indexOf("ATG",where);
        
        System.out.println("ATG found:" +startIndex);
        if(startIndex==-1)
        {
            return"";
        }
       
            int TAACodonIndex= findStopCodon(dna,startIndex,"TAA");
            System.out.println("TAA found:" +TAACodonIndex);
            int TGACodonIndex=findStopCodon(dna,startIndex,"TGA");
            System.out.println("TGA found:" +TGACodonIndex);
            int TAGCodonIndex=findStopCodon(dna,startIndex,"TAG");
            System.out.println("TAG found:" +TAGCodonIndex);
     
            int minIndex=0;
            if(TAACodonIndex==-1 || (TGACodonIndex!=-1 && TGACodonIndex<TAACodonIndex))
            {
                minIndex=TGACodonIndex;
            }
            else
            {
              minIndex= TAACodonIndex;
            }
            
            if(minIndex==-1 || (TAGCodonIndex!=-1 && TAGCodonIndex<minIndex))
            {
                minIndex =TAGCodonIndex;
            }
            if(minIndex==-1)
            {
                return "";
            }
            
            return dna.substring(startIndex,minIndex+3);
        
        
       
    }
    
    public StorageResource getAllGenes(String dna)
    {
        
        StorageResource genes = new StorageResource();
        int startIndex=0;
        while(true)
        {
            String CurrentGene=FindGene(dna,startIndex);
       
            if(CurrentGene.isEmpty())
            {
               
                break;
            }
  
                genes.add(CurrentGene);
                startIndex=dna.indexOf(CurrentGene,startIndex)+CurrentGene.length();

        }
        
        return genes;
    }
    
    public float cgRatio(String dna)
    {

        int indexG=0;
        int indexC=0;
        int countG=0;
        int countC=0;
        float CGratio=0;
       
        while(true)
        {
            if(dna.indexOf("G",indexG)!=-1)
            {
                countG=countG+1;
                indexG=dna.indexOf("G",indexG)+1;
                
           }
        else{
            break;
        }
    }
    
        while(true)
        {
             if(dna.indexOf("C",indexC)!=-1)
            {
                countC=countC+1;
                indexC=dna.indexOf("C",indexC)+1;
                
           }
        else{
            
           break;
        }
       
        }
        
      
        
        int CG=countG+countC;
        float CGf=CG;
        float length=dna.length();
        CGratio=CGf/length;
        
        
    
    return CGratio;
}

 public void processGenes(StorageResource sr)
 
 { int length=0;
   int count9=0;
   int countCG=0;
   float CG=0;
   int newLen=0;
   int LarLen=0;
   int count =0;
   double thes=0.35;
   int CTGcount=0;
   int CTGindex=0;
   
   
     for(String s:sr.data())
     {
         if(s.length()>60)
         {
           
            System.out.println("Length is > 60: "+s);
            
            count9 = count9 +1;
            
            }
            
        }
        System.out.println("Count for 60: "+count9);
       for(String s:sr.data())
       {
           CG=cgRatio(s);
           
           if(CG>thes)
           {
               countCG=countCG+1;
               System.out.println("CGratio > 0.35: "+s);
                
            }
           
           
        }
        System.out.println("Count for >.35: "+ countCG);
        for(String s:sr.data())
        {
       newLen=s.length();
            if(newLen>LarLen)
            {
               LarLen= newLen;
            }
    }
    for(String s:sr.data())
    {
        count = count+1;
    }
    
    System.out.println("Largest length: "+LarLen);
    System.out.println("Genes: "+count);
    }
    public int countCTG(FileResource fr)
    {
        
        String dna = fr.asString();
        dna=dna.toUpperCase();
        int CTGindex=0;
        int CTGcount=0;
        while(true)
        {
           if(dna.indexOf("CTG",CTGindex)!=-1)
           { CTGcount=CTGcount+1;
             CTGindex=dna.indexOf("CTG",CTGindex)+3;
               
            }
            else 
            {
                break;
            }
        }
        return  CTGcount;
    }

  //  public void testGetAllGenes()
  //  {
 //       StorageResource genes = new StorageResource();
 //       String dna="ATGATCTAATTTATGCTGCAACGGTGAAGAATGAACGACTGA";
  //      genes=getAllGenes(dna);
  //      for(String s:genes.data())
   //     {
   //         System.out.println(s);
   //     }
        
        
   // }
    
  //  public void testCgRatio()
   // {
  //      String dna ="ATGCCATAG";
  //      float CG=cgRatio(dna);
 //       System.out.println(CG);
  //  }
    
    public void testProcessGenes()
    {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna=dna.toUpperCase();
        System.out.println(dna);
        StorageResource genes = new StorageResource();
        genes=getAllGenes(dna);
        processGenes(genes);
        int CTG=countCTG(fr);
        System.out.println("CTG count: "+ CTG);
        
        
    }
    
    public void testOn(String dna)
    {
        System.out.println("Testing getAllGenes on "+ dna);
        StorageResource genes = getAllGenes(dna);
        int count =0;
        for (String g: genes.data())
        {
            System.out.println(g);
            count=count+1;
        }
        System.out.println(count);
        
    }
}
    
