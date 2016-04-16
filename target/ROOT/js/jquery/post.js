var $post = (function(){
	var tool,
		config,
		Post,
		Ajax,
		manage;
	
	manage = function(){
		var _self 	= this,
			_rtn 	= function( url , data , callback , type ){
				_self.post.apply( _self , arguments );
			};
		_rtn.ajax 	= function(){ _self.ajax.apply( _self , arguments ); };
		_rtn.post 	= function(){ _self.post.apply( _self , arguments ); };
		_rtn.get 	= function(){ _self.get.apply( _self , arguments ); };
		_rtn.error 	= function(){ _self.error.apply( _self , arguments ); };
		_rtn.alert 	= function(){ _self.alert.apply( _self , arguments ); };
		_rtn.uncover= function(){ _self.uncover.apply( _self , arguments ); };
		_rtn.cover  = function(){ _self.cover.apply( _self , arguments );}
		return _rtn;
	};

	manage.fn = manage.prototype = {
		"ajax" : function( url , data , callback , type , opts ){
			if( !url || typeof url !== "string" ){
				return false;
			} else if( typeof data == "function" ){
				if( callback ){
					opts 		= type;
					type 		= callback;
				};
				callback 	= data;
				data 		= {};
			};
			return new Post( url , data , callback , type || "get" , opts );
		},
		"post" : function( url , data , callback , opts ){
			this.ajax( url , data , callback , "post" , opts );
		},
		"get" : function( url , data , callback , opts ){
			this.ajax( url , data , callback , "get" , opts );
		},
		/*!
		 * 	设置通配的错误处理
		 *	@errors 	{func}
		 *	@errors		{array}
		 *		@func 		{func}
		 */
		"error" : function( errors ){
			if( typeof errors == "function" ){
				errors = [ errors ];
			} else if( !( errors instanceof Array ) ){
				return false;
			};
			config.errors = config.errors.concat( errors );
		},
		"alert" : function( str ){
			if( str ){
				Post.fn.items++;
				tool.cover();
				config.modal.text.html( str );
				config.modal.dialog.show();
			};
		},
		uncover : function(){
			tool.uncover();
		},
		cover : function(){
			tool.cover();
		}
	};

	manage.fn.constructor = manage;

	Post = function( url , data , callback , type , opts ){
		opts = tool.get_opts( opts );

		var _self = this,
			_opts = {
				"type" 		: type,
				"url" 		: url,
				"data" 	 	: data,
				"beforeSend" 	: function( xhr ){
					if( opts.header ){
						for( var a in opts.header ){
							xhr[ a ] = opts.header[ a ]; 
						};
					};
				},
				"success" 	: function( rtn ){
					if( typeof rtn == "string" ){
						rtn = $.parseJSON( rtn );
					};
					if( !--_self.items ){
						tool.uncover();
					};
					for( var i = opts.errors.length; i--; ){
						if( opts.errors[ i ]( rtn ) !== undefined ){
							return false;
						};
					};
					if( callback ){
						callback( rtn );
					};
				},
				"error" 	: function( rtn ){
					manage.fn.alert( "系统错误！" );
				},
				timeout 	: opts.timeout,
				dataType 	: "json"
			};
		this.items++;
		tool.cover();
		$.ajax( _opts );
	};

	Post.fn = Post.prototype;

	Post.fn.items = 0;

	tool = {
		cover : function(){
			config.modal.cover.css( {
				"height" 	: $( document.body ).height() 	
			} ).show();
		},
		uncover : function(){
			config.modal.cover.hide();
		},
		get_opts : function( opts ){
			return !opts || typeof opts !== "object" ? $.extend( config ) : 
						( function( opts ){
							opts.timeout 	= opts.timeout || config.timeout;
							if( opts.header ){
								opts.header = $.extend( config.header , opts.header );
							};
							return opts;
						} )( opts );
		},
		dialog_close : function(){
			if( !--Post.fn.items ){
				tool.uncover();
			};
			config.modal.dialog.hide();
		},
		dialog_event : function(){
			config.modal.dialog.find( "[func='close']" ).on( "click" , function(){
				tool.dialog_close();
			} );
		},
		config : function(){
			config = {
				modal 		: {
					cover 	: tool.get_cover(),
					dialog 	: tool.get_dialog(),
					text 	: 0
				},
				header 		: {},
				errors 		: [],
				timeout 	: 20000
			};

			config.modal.text = config.modal.dialog.find( "[func='text']" );
			tool.dialog_event();
		}
	};

	tool.config();

	return new manage();
})();