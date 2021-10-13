# projectCovid
#แก้ ERROR database เราเป็น ตัวพิมพ์ใหญ่ ตัว jpa มันไปแปลงเป็นตัวพิมพ์เล็กมันเลย error
  spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImp
  เอาไปใส่ใน application.properties
