// Genera datos únicos en cada escenario para no chocar con otros usuarios del sandbox público.
// El id queda por debajo de 2^53 para que JavaScript lo represente sin perder precisión.
function() {
  var stamp = java.lang.System.currentTimeMillis();
  var random = Math.floor(Math.random() * 1000);
  var username = 'sofka_qa_' + stamp + '_' + random;
  return {
    userId: stamp * 1000 + random,
    username: username,
    email: username + '@example.com',
    updatedEmail: username + '.updated@example.com'
  };
}
