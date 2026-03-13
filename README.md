# FinalProject - Selenium Java Test Automation

Framework automation test cho website CMS: `https://cms.anhtester.com/login`.

## 1. Mục tiêu dự án
Dự án kiểm thử tự động các chức năng chính của CMS theo mô hình Page Object Model (POM):
- `Login`
- `Dashboard` (điều hướng trang)
- `Brand` (thêm/xóa)
- `Category` (thêm/sửa/xóa/tìm kiếm)
- `Product` (thêm/sửa/xem chi tiết/tìm kiếm)
- `Flow end-to-end`: Login -> Add Brand -> Add Category -> Add Product

## 2. Công nghệ sử dụng
- Java `17`
- Maven
- Selenium `4.35.0`
- TestNG `7.11.0`
- Extent Report `5.1.1`
- Allure TestNG `2.27.0`
- Apache POI (đọc dữ liệu Excel)

## 3. Cấu trúc thư mục chính
```text
src/main/java
  |- constants/        # ConfigData
  |- drivers/          # DriverManager (ThreadLocal WebDriver)
  |- helpers/          # Properties/Excel/Capture/System helper
  |- keywords/         # WebUI keywords (click, sendKeys, wait,...)
  |- reports/          # Extent + Allure helper
  |- utils/            # Logger

src/test/java
  |- AT04_CMS/pages/      # Page Object classes
  |- AT04_CMS/testcases/  # Test cases
  |- common/              # BaseTest
  |- listeners/           # TestListener
  |- dataProvider/        # DataProvider từ Excel

src/test/resources
  |- configs/configs.properties
  |- suite/*.xml          # Suite TestNG
  |- testData/*.xlsx      # Test data Excel

report/                   # Extent, screenshot, video (khi chạy test)
allure-results/           # Kết quả Allure
```

## 4. Yêu cầu môi trường
- JDK 17
- Maven 3.8+
- Trình duyệt đã cài sẵn: Chrome / Edge / Firefox

## 5. Cấu hình chạy test
Chỉnh file: `src/test/resources/configs/configs.properties`

Các key quan trọng:
- `BROWSER`: `chrome` | `edge` | `firefox`
- `URL`, `EMAIL`, `PASSWORD`
- `EXCEL_PATH`
- `SCREENSHOT_FAIL` / `SCREENSHOT_PASS`: `on` hoặc `off`
- `VIDEO_RECORD`: `on` hoặc `off`

Lưu ý:
- `BaseTest` nhận browser qua tham số TestNG `BROWSER` (trong file suite) hoặc mặc định `chrome`.
- Dự án đang dùng listener `TestListener` để chụp ảnh/report theo trạng thái test.

## 6. Cách chạy test
Cài dependency:
```bash
mvn clean install -DskipTests
```

Chạy toàn bộ test theo suite tổng:
```bash
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/suite/CMS.xml
```

Chạy từng module:
```bash
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/suite/LoginTest.xml
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/suite/DashboardTest.xml
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/suite/BrandTest.xml
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/suite/CategoryTest.xml
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/suite/ProductTest.xml
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/suite/FlowTest.xml
```

## 7. Report và artifacts
- Extent Report: `report/ExtentReport/ExtentReport.html`
- Screenshot fail/pass: `report/Screenshots/`
- Video record (nếu bật): `report/VideoRecords/`
- Allure raw result: `allure-results/` (hoặc cấu hình Maven nếu bạn đổi output)

Nếu đã cài Allure CLI, có thể mở report local:
```bash
allure serve allure-results
```

## 8. Test data
File dữ liệu chính:
- `src/test/resources/testData/data-eCommerceCMS.xlsx`

Các sheet đang được dùng trong test:
- `BrandData`
- `CategoryData`
- `ProductData`

## 9. Ghi chú
- Framework hỗ trợ chạy song song theo `classes` hoặc `methods` tùy file suite.
- `DriverManager` sử dụng `ThreadLocal` để hỗ trợ parallel execution.
