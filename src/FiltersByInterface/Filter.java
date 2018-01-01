package FiltersByInterface;

import Main.INITIAL;
import Main.Wifi;

/**
 *     --- Filters explain ---
 *     The filters pack contains :
 *     the Filter by time (Start to End time line ) 
 *     Filter by location (Min - Max location points , geographic range )
 *     Filter by Device , that represent the result of only the device that make that scan.
 *     
 *     all the filter works basically in the same way , they get the parameters form the user (at the future from the GUI interface) 
 *     s.t min max range , time start - end , device name, and BOOLEAN flag .
 *     
 *     The boolean flag ( '!' 'NOT' operation )- is made to checkout if we want to filter out data by the given input or by the completely opposite 
 *      s.t if we input that we want the data that was created by the device "Asus" , then if we pass the boolean as -true -> 
 *      the output data will contain all wifi's that was collected by the "Asus" device, however if we were passing the boolean flag 
 *      as-False -> then the out put would be the all wifi's that was not collected by the "Asus" device. 
 *      
 *      how to work with the filter?? [comments for developers team]
 *      1. at runnable class creat an type of the filter like that : 
 *      			` FilterByDeviceInput fbs = new FilterByDeviceInput("02:8d:db:6e:71:c1", INITIAL.CSVWifisInOrder,false); `
 *      	2. now you can use the builed up collection in each filter by :
 *      			` fbs.getResult(); ` 
 *      		that will return the filtered collection.
 *      
 *      
 *      NOTE** : they may be one problem , as we seen sometimes we get the wrong Time format , i.e 
 *      			Lecture csv time formats: yyyy-MM-DD  HH:MM:ss
 *      			our csv time formats (sometimes) : DD/MM/yyyy  HH:MM:ss
 *      be aware of that confusion , and stay calm.
 *   
 *     
 * @author recon
 *
 */

public interface Filter {

	public boolean passes(Wifi a);
}
