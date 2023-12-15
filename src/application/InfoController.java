package application;

import data.DataTableAdapter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class InfoController {

    @FXML
    private GridPane mainGrid;

    @FXML
    private Pagination pagination;

    @FXML
    private void initialize() {
    	pagination.setPageFactory((pageIndex) -> {
    		
            if (pageIndex == 0) {
            	return this.getMainPageInfo();
            }

            if (pageIndex == 1) {
            	return this.getDataPageInfo();
            }
            
            if (pageIndex == 2) {
            	return this.getDiagramPageInfo();
            }
            
            return new VBox();
        });
    }
    
    private VBox getMainPageInfo() {
    	Label label1 = new Label("Главная страница");
        label1.setFont(new Font("Arial", 24));

        Label label2 = new Label("Главная страница является начальной страницей приложения. На ней вы можете просмотреть имеющиеся таблицы данных, а также импортировать файл формата csv в приложение с помощью кнопки \"Добавить\".\n"
        						+ "На каждой странице присутствует боковая панель, с помощью которой вы можете переходить на другие страницы: главная, данные, диаграммы, справка (текущая страница)");
        label2.setFont(new Font("Arial", 15));
        
        return new VBox(label1, label2);
    }
    
    private VBox getDataPageInfo() {
    	Label label1 = new Label("Страница \"Данные\"");
        label1.setFont(new Font("Arial", 24));

        Label label2 = new Label("Данная страница предназначена для просмотра, добавления и удаления таблиц данных.\nС помощью кнопки \"Добавить\" вы можете добавить новую таблицу данных. Импорт возможен только файлов формата csv.\n"
        						+ "Слева в списке отображаются названия таблиц данных, при нажатии ЛКМ по элементу списка 1 раз справа в таблице отобразятся данные из файла.\nПри нажатии ЛКМ по элементу списка 2 раза, можно изменить название таблицы данных.\n"
        						+ "При нажатии на кнопку \"Удалить\", можно удалить таблицу из списка и из приложения");
        label2.setFont(new Font("Arial", 15));
        
        return new VBox(label1, label2);
    }
    
    private VBox getDiagramPageInfo() {
    	Label label1 = new Label("Страница \"Диаграммы\"");
        label1.setFont(new Font("Arial", 24));

        Label label2 = new Label("Данная страница позволяет строить диаграммы по импортированным данным. В данный момент возможно построить следующие виды диаграмм: линейная, диаграмма области, столбчатая, круговая и точечная.\n"
        						+ "Для построения диаграммы нужно выбрать нужную таблицу данных, а также тип диаграммы.\nДля каждой диаграммы есть своё множество настроек. Настройки могут быть такими: выбор столбца для каждой оси, изменение цвета диаграммы и выбор диапазона строк данных из таблицы для построения диаграммы.");
        label2.setFont(new Font("Arial", 15));

        return new VBox(label1, label2);
    }
    
}