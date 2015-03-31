<%@ page pageEncoding="UTF-8" %>
<html>
<head>
<title>Kullanıcı Yönetim</title>
<!-- Include one of jTable styles. -->
<link href="css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<link href="css/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.realperson.css" rel="stylesheet" type="text/css" />

<!-- Include jTable script file. -->
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>
<script src="js/jquery.validationEngine.js" type="text/javascript"></script>
<script src="js/jquery.validationEngine-tr.js" type="text/javascript"></script>
<script src="js/jquery.plugin.js" type="text/javascript"></script>
<script src="js/jquery.realperson.js" type="text/javascript"></script>
<script src="js/jquery.maskedinput.js" type="text/javascript"></script>


<script type="text/javascript">
	$(document).ready(function() {
        //Localization texts
        var turkishMessages = {
            serverCommunicationError: 'Sunucu ile iletişim kurulurken bir hata oluştu.',
            loadingMessage: 'Kayıtlar yükleniyor...',
            noDataAvailable: 'Hiç kayıt bulunmamaktadır!',
            addNewRecord: 'Yeni kayıt ekle',
            editRecord: 'Kayıt düzenle',
            areYouSure: 'Emin misiniz?',
            deleteConfirmation: 'Bu kayıt silinecektir. Emin misiniz?',
            save: 'Kaydet',
            saving: 'Kaydediyor',
            cancel: 'İptal',
            deleteText: 'Sil',
            deleting: 'Siliyor',
            error: 'Hata',
            close: 'Kapat',
            cannotLoadOptionsFor: '{0} alanı için seçenekler yüklenemedi!',
            pagingInfo: 'Toplam {2}, {0} ile {1} arası gösteriliyor',
            canNotDeletedRecords: '{1} kayıttan {0} adedi silinemedi!',
            deleteProggress: '{1} kayıttan {0} adedi silindi, devam ediliyor...'
        };
        
		$('#UserTableContainer').jtable({
			messages: turkishMessages,
			title : 'Kullanıcı Listesi',
			actions : {
				listAction : 'UserControllerServlet?action=list',
				createAction : 'UserControllerServlet?action=create',
				updateAction : 'UserControllerServlet?action=update',
				deleteAction : 'UserControllerServlet?action=delete'
			},
			fields : {
				userId : {
					title : 'No',
					width : '30%',
					key : true,
					list : true,
					edit : false,
					create : true
				},
				name : {
					title : 'Ad',
					width : '30%',
					edit : true
				},
				surname : {
					title : 'Soyad',
					width : '30%',
					edit : true
				},
				phoneNumber : {
					title : 'Telefon',
					width : '20%',
					edit : true
				},
				captcha : {
					title : '',
					width : '20%',
					edit : false,
					hidden : true
				}
			},
            formCreated: function (event, data) {
            	data.form.css('width','300px'); 
                data.form.find('input[name="userId"]').addClass(
                  'validate[required]');
                data.form.find('input[name="name"]').addClass(
                  'validate[required]');
                data.form.find('input[name="surname"]').addClass(
                  'validate[required]');
                data.form.find('input[name="phoneNumber"]').mask("999-999 9999");
                data.form.find('input[name="captcha"]').realperson();
                data.form.validationEngine();
            },
            formSubmitting: function (event, data) {11111
                return data.form.validationEngine('validate');
            },
            formClosed: function (event, data) {
                data.form.validationEngine('hide');
                data.form.validationEngine('detach');
            }
		});
		$('#UserTableContainer').jtable('load');
	});
</script>

</head>
<body>
	<div style="width: 80%; margin-right: 10%; margin-left: 10%;">
		<div id="UserTableContainer"></div>
		<footer>
            Süleyman Gümrükçü
        </footer>
		
	</div>
</body>
</html>