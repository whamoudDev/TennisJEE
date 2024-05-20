/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import beans.BeanException;
import beans.EpreuveData;
import java.util.ArrayList;

/**
 *
 * @author WalidDev
 */
public interface IEpreuveDao {

	public ArrayList<EpreuveData> listerEpreuveData() throws BeanException;

	public ArrayList<EpreuveData> listerEpreuveDataFiltered(String annee, String type) throws BeanException;

}
