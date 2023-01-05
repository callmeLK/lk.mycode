package entites;

import annotation.ExportExcel;
import annotation.ImportExcel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


/**
 * 车辆图片信息自定义时间
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("document_car_info")
public class DocumentCarInfoTimeFormatVO {

    private Long id;

    //    @ApiModelProperty("三级菜单id")
//    @ExportExcel(titleName = "菜单")
    Long menuId;

    //    @ApiModelProperty("三级菜单名字")
    String menuName;
    @ImportExcel(order = 1)
    @ExportExcel(titleName = "品牌名称")
//    @ApiModelProperty("品牌名称")
    String carName;

    @ImportExcel(order = 2)
    @ExportExcel(titleName = "车系")
//    @ApiModelProperty("品牌-中系、日系、美系")
    String carBrand;

    //    @ApiModelProperty("品牌-中系、日系、美系")
    String carBrandId;
    @ImportExcel(order = 3)
    @ExportExcel(titleName = "车辆类型")
//    @ApiModelProperty("车辆类型")
    String carType;

    //    @ApiModelProperty("车辆类型id")
    String carTypeId;

    @ImportExcel(order = 4)
    @ExportExcel(titleName = "汽车级别")
//    @ApiModelProperty("汽车级别")
    String carLevel;

    //    @ApiModelProperty("汽车级别id")
    String carLevelId;

    @ImportExcel(order = 5)

    @ExportExcel(titleName = "上市年份")
//    @ApiModelProperty("上市年份(YYYY)")
    String carMarketTime;

    //    @ApiModelProperty("评测年份(YYYY)")
    String carEvaluateTime;

    //    @ApiModelProperty("评分")
    String carScore;

    @ImportExcel(order = 6)
    @ExportExcel(titleName = "最低售价")
//    @ApiModelProperty("最低售价")
    BigDecimal carLowerPrice;
    @ImportExcel(order = 7)
    @ExportExcel(titleName = "最高售价")
//    @ApiModelProperty("最高售价")
    BigDecimal carHighPrice;

    @ImportExcel(order = 8)
    @ExportExcel(titleName = "汽车尺寸长")
//    @ApiModelProperty("汽车尺寸长")
    BigDecimal carSizeLong;

    @ImportExcel(order = 9)
    @ExportExcel(titleName = "汽车尺宽")
//    @ApiModelProperty("汽车尺宽")
    BigDecimal carSizeWidth;

    @ImportExcel(order = 10)
    @ExportExcel(titleName = "汽车尺寸高")
//    @ApiModelProperty("汽车尺寸高")
    BigDecimal carSizeHigh;

    @ImportExcel(order = 11)
    @ExportExcel(titleName = "汽车轴距")
//    @ApiModelProperty("汽车轴距")
    BigDecimal carWheelbase;

    //    @ApiModelProperty("汽车上传图片缩略图")
    String carPic;

    //    @ApiModelProperty("汽车图片缩略图全url")
    String carPicUrl;

    //    @ApiModelProperty("汽车上传图片高清")
    String carPicHd;

    //    @ApiModelProperty("汽车图片高清图全url")
    String carPicHdUrl;

    //    @ApiModelProperty("权限是否分享 0否1是")
    Integer toShared;

    //    @ApiModelProperty("该用户是否收藏 1是 0否")
    Integer hasCollection = 0;

    //    @ApiModelProperty("创建时间")
    String createTime;


//    public String getCarPicUrl() {
//        return ShareUtils.serverAddress+ShareUtils.getImgUrl(carPic);
//    }

//    public String getCarPicHdUrl() {
//        return ShareUtils.serverAddress+ShareUtils.getImgUrl(carPicHd);
//    }

}
