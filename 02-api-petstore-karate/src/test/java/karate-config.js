function fn() {
  // Ambiente con -Dkarate.env; si no se indica se usa dev, el sandbox público de PetStore
  var env = karate.env || 'dev';
  var baseUrls = {
    dev: 'https://petstore.swagger.io/v2'
  };
  if (!baseUrls[env]) {
    throw 'Unknown karate.env: ' + env + '. Allowed values: ' + Object.keys(baseUrls).join(', ');
  }
  karate.log('karate.env:', env, '| baseUrl:', baseUrls[env]);

  // Tiempos máximos de conexión y de respuesta, en milisegundos
  karate.configure('connectTimeout', 10000);
  karate.configure('readTimeout', 20000);
  // La API responde en JSON o en XML según el header Accept; se pide JSON de forma explícita
  karate.configure('headers', { Accept: 'application/json' });
  // Request y response con formato legible en el log y en el reporte
  karate.configure('logPrettyRequest', true);
  karate.configure('logPrettyResponse', true);

  return {
    env: env,
    baseUrl: baseUrls[env]
  };
}
